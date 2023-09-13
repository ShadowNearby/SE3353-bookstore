package com.example.bookstore.dao.daoimpl;

import com.example.bookstore.dao.BookDao;
import com.example.bookstore.entity.Book;
import com.example.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    private final BookRepository bookRepository;

    public BookDaoImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findBooksByDeletedNot(true);
//        return bookRepository.findAll();
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

    @Override
    public void deleteBook(Book book) {
        book.setDeleted(true);
        bookRepository.save(book);
    }

}
