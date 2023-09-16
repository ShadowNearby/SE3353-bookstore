package com.example.bookstore.dao.daoimpl;

import com.alibaba.fastjson2.JSON;
import com.example.bookstore.dao.BookDao;
import com.example.bookstore.entity.Book;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.util.CacheKeyConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public class BookDaoImpl implements BookDao {
    private final BookRepository bookRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final Logger log = LoggerFactory.getLogger(BookDaoImpl.class);

    public BookDaoImpl(BookRepository bookRepository, RedisTemplate<String, String> redisTemplate) {
        this.bookRepository = bookRepository;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List<Book> getBooks() {
        var key = CacheKeyConstant.ALL_BOOK;
        var cache_result = redisTemplate.opsForValue().get(key);
        if (cache_result != null) {
            log.info("cache hit {}", key);
            return JSON.parseArray(cache_result, Book.class);
        }
        log.warn("cache miss {}", key);
        var all_books = bookRepository.findBooksByDeletedNot(true);
        redisTemplate.opsForValue().set(key, JSON.toJSONString(all_books));
        log.info("cache set {}", key);
        return all_books;
    }

    @Override
    public void updateBook(Book book) {
        var all_book_key = CacheKeyConstant.ALL_BOOK;
        var book_key = String.format("book-id-%d", book.getId());
        bookRepository.save(book);
        redisTemplate.delete(Stream.of(all_book_key, book_key).toList());
        log.info("cache remove {},{}", all_book_key, book_key);
    }

    @Override
    public Book getBookById(Long id) {
        var key = String.format("book-id-%d", id);
        var cache_result = redisTemplate.opsForValue().get(key);
        if (cache_result != null) {
            log.info("cache hit {}", key);
            return JSON.parseObject(cache_result, Book.class);
        }
        log.warn("cache miss {}", key);
        var book = bookRepository.findById(id);
        if (book.isPresent()) {
            redisTemplate.opsForValue().set(key, JSON.toJSONString(book.get()));
            log.info("cache set {}", key);
            return book.get();
        }
        log.error("book id {} is not present", id);
        return null;
    }

    @Override
    public Book getBookByName(String name) {
        var key = String.format("book-name-%s", name);
        var cache_result = redisTemplate.opsForValue().get(key);
        if (cache_result != null) {
            log.info("cache hit {}", key);
            return JSON.parseObject(cache_result, Book.class);
        }
        log.warn("cache miss {}", key);
        var book = bookRepository.findByName(name);
        if (book.isPresent()) {
            redisTemplate.opsForValue().set(key, JSON.toJSONString(book.get()));
            log.info("cache set {}", key);
            return book.get();
        }
        log.error("book name {} is not present", name);
        return null;
    }


    @Override
    public Integer addInventory(String name, Integer count) {
        Book book = getBookByName(name);
        book.setInventory(book.getInventory() + count);
        updateBook(book);
        return book.getInventory();
    }

    @Override
    public Integer subInventory(String name, Integer count) {
        Book book = getBookByName(name);
        Integer inv = book.getInventory();
        if (inv < count)
            return -1;
        book.setInventory(inv - count);
        updateBook(book);
        return inv - count;
    }

    @Override
    public void deleteBook(Book book) {
        book.setDeleted(true);
        updateBook(book);
    }

}
