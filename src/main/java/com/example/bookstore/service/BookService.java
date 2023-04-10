package com.example.bookstore.service;

import com.example.bookstore.entity.Book;
import com.example.bookstore.util.request.BookStatisticsForm;
import com.example.bookstore.util.request.StatisticForm;

import java.util.List;


public interface BookService {
    List<Book> getBooks();

    Book getBookById(Long id);

    Book getBookByName(String name);

    List<Book> getBooksByTagNames(List<String> tagNames);

    void addBook(String name, String image, String desc, String author, String isbn, Double price, Integer inventory, List<Long> tagIds);

    void putBook(Long id, String name, String image, String desc, String author, String isbn, Double price, Integer inventory, List<Long> tagIds);

    void deleteBook(Long id);

    Integer addInventory(String name, Integer count);

    Integer subInventory(String name, Integer count);

    List<BookStatisticsForm> statistics(StatisticForm statisticForm);
}
