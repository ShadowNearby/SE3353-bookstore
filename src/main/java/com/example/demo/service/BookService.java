package com.example.demo.service;

import com.example.demo.entity.Book;

import java.util.List;


public interface BookService {
    List<Book> getBooks();

    Book getBookById(Long id);

    Book getBookByName(String name);

    List<Book> getBooksByTagNames(List<String> tagNames);

    void addBook(String name, String image, String desc, String author, String isbn, Double price, Integer inventory, String tagName);

    Integer addInventory(String name, Integer count);

    Integer subInventory(String name, Integer count);
}
