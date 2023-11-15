package com.mainservice.service;

import com.mainservice.entity.Book;
import com.mainservice.util.request.BookStatisticsForm;
import com.mainservice.util.request.StatisticForm;

import java.util.List;


public interface BookService {
    List<Book> getBooks();

    Book getBookById(Long id);

    Book getBookByName(String name);

    List<Book> getBookByTypeName(String name);


    void addBook(String name, String image, String desc, String author, String isbn, Double price, Integer inventory);

    void putBook(Long id, String name, String image, String desc, String author, String isbn, Double price, Integer inventory);

    void deleteBook(Long id);

    Integer addInventory(String name, Integer count);

    Integer subInventory(String name, Integer count);

    List<BookStatisticsForm> statistics(StatisticForm statisticForm);
}
