package com.example.demo.dao.daoimpl;

import com.example.demo.dao.BookDao;
import com.example.demo.entity.Book;
import com.example.demo.entity.Tag;
import com.example.demo.repository.BookRepository;
import org.springframework.stereotype.Repository;


import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class BookDaoImpl implements BookDao {

    private final BookRepository bookRepository;

    public BookDaoImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book getBookByName(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    public List<Book> getBooksByTags(List<Tag> tags) {
        return bookRepository.findAllByTagsIn(tags);
    }

    @Override
    public Integer addInventory(String name, Integer count) {
        Book book = getBookByName(name);
        book.setInventory(book.getInventory() + count);
        bookRepository.save(book);
        return book.getInventory();
    }

    @Override
    public Integer subInventory(String name, Integer count) {
        Book book = getBookByName(name);
        Integer inv = book.getInventory();
        if (inv < count)
            return -1;
        book.setInventory(inv - count);
        bookRepository.save(book);
        return inv - count;
    }

}
