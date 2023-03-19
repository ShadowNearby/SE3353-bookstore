package com.example.demo.dao;

import com.example.demo.entity.Book;
import com.example.demo.entity.Tag;

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
