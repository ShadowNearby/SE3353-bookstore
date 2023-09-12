package com.example.bookstore.dao;

import com.example.bookstore.entity.Book;

import java.util.List;

public interface BookDao {
    List<Book> getBooks();

    void addBook(Book book);

    Book getBookById(Long id);

    Book getBookByName(String name);

    Integer addInventory(String name, Integer count);

    Integer subInventory(String name, Integer count);

    void deleteBook(Book book);
}
