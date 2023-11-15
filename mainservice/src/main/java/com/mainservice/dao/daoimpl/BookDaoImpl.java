package com.mainservice.dao.daoimpl;

import com.alibaba.fastjson2.JSON;
import com.mainservice.dao.BookDao;
import com.mainservice.entity.Book;
import com.mainservice.repository.BookRepository;
import com.mainservice.util.CacheKeyConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
        try {
            var cache_result = redisTemplate.opsForValue().get(key);
            if (cache_result != null) {
                log.info("cache hit {}", key);
                return JSON.parseArray(cache_result, Book.class);
            }
        } catch (Exception ignored) {
            log.error("redis error");
        }
        log.warn("cache miss {}", key);
        var all_books = bookRepository.findBooksByDeletedNot(true);
        try {
            redisTemplate.opsForValue().set(key, JSON.toJSONString(all_books));
            log.info("cache set {}", key);
        } catch (Exception ignored) {
            log.error("redis error");
        }
        return all_books;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateBook(Book book) {
        bookRepository.save(book);
        try {
            var all_book_key = CacheKeyConstant.ALL_BOOK;
            var book_key = String.format("book-id-%d", book.getId());
            redisTemplate.delete(Stream.of(all_book_key, book_key).toList());
            log.info("cache remove {}, {}", all_book_key, book_key);
        } catch (Exception ignore) {
            log.error("redis error");
        }

    }

    @Override
    public Book getBookById(Long id) {
        var key = String.format("book-id-%d", id);
        try {
            var cache_result = redisTemplate.opsForValue().get(key);
            if (cache_result != null) {
                log.info("cache hit {}", key);
                return JSON.parseObject(cache_result, Book.class);
            }
            log.warn("cache miss {}", key);
        } catch (Exception ignored) {
            log.error("redis error");
        }
        var book = bookRepository.findById(id);
        if (book.isPresent()) {
            try {
                redisTemplate.opsForValue().set(key, JSON.toJSONString(book.get()));
                log.info("cache set {}", key);
            } catch (Exception ignored) {
                log.error("redis error");
            }
            return book.get();
        }
        log.error("book id {} is not present", id);
        return null;
    }

    @Override
    public Book getBookByName(String name) {
        var key = String.format("book-name-%s", name);
        try {
            var cache_result = redisTemplate.opsForValue().get(key);
            if (cache_result != null) {
                log.info("cache hit {}", key);
                return JSON.parseObject(cache_result, Book.class);
            }
            log.warn("cache miss {}", key);
        } catch (Exception ignored) {
            log.error("redis error");
        }
        var book = bookRepository.findByNameContains(name);
        if (book.isPresent()) {
            try {
                redisTemplate.opsForValue().set(key, JSON.toJSONString(book.get()));
                log.info("cache set {}", key);
            } catch (Exception ignored) {
                log.error("redis error");
            }
            return book.get();
        }
        log.error("book name {} is not present", name);
        return null;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer addInventory(String name, Integer count) {
        Book book = getBookByName(name);
        book.setInventory(book.getInventory() + count);
        updateBook(book);
        return book.getInventory();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
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
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteBook(Book book) {
        book.setDeleted(true);
        updateBook(book);
    }

}
