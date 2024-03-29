package com.mainservice.controller;

import com.mainservice.entity.Book;
import com.mainservice.extern.impl.SparkMapReduceService;
import com.mainservice.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Transactional
public class BookController {
    private final BookService bookService;
    private final SparkMapReduceService mapReduceService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public BookController(BookService bookService, SparkMapReduceService mapReduceService) {
        this.bookService = bookService;
        this.mapReduceService = mapReduceService;
    }

    @RequestMapping(value = "/api/book/{bookId}", method = RequestMethod.GET)
    public Book getBookById(@PathVariable("bookId") Long bookId) {
        return bookService.getBookById(bookId);
    }

    @RequestMapping(value = "/api/book", method = RequestMethod.GET)
    public List<Book> getBooksByTypeName(@RequestParam(value = "type", required = false) String typeName, @RequestParam(value = "name", required = false) String bookName) {
        if (typeName != null && !typeName.isEmpty()) {
            log.info("type {}", typeName);
            return bookService.getBookByTypeName(typeName);
        }
        log.info("book name {}", bookName);
        var books = new ArrayList<Book>();
        books.add(bookService.getBookByName(bookName));
        return books;
    }


    @RequestMapping(value = "/api/books", method = RequestMethod.GET)
    public List<Book> getAllBooks() {
        return bookService.getBooks();
    }


    @RequestMapping(value = "/api/book/addinv", method = RequestMethod.POST)
    public Integer addInventory(@RequestParam("name") String name, @RequestParam("count") Integer count) {
        return bookService.addInventory(name, count);
    }

    @RequestMapping(value = "/api/book/subinv", method = RequestMethod.POST)
    public Integer subInventory(@RequestParam("name") String name, @RequestParam("count") Integer count) {
        return bookService.subInventory(name, count);
    }

    @RequestMapping(value = "/api/book/mr", method = RequestMethod.POST)
    public String MapReduce() {
        return mapReduceService.run();
    }

}
