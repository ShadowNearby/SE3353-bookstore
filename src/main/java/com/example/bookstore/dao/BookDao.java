package com.example.bookstore.dao;

import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Tag;

import java.util.List;

public interface BookDao {
    List<Book> getBooks();

    void addBook(Book book);

    Book getBookById(Long id);

    Book getBookByName(String name);

    List<Book> getBooksByTags(List<Tag> tags);

    Integer addInventory(String name, Integer count);

    Integer subInventory(String name, Integer count);
}
