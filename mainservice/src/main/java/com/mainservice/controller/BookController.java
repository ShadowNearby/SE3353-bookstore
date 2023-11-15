package com.mainservice.controller;

import com.mainservice.entity.Book;
import com.mainservice.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
public class BookController {
    private final BookService bookService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/api/book/{bookId}", method = RequestMethod.GET)
    public Book getBookById(@PathVariable("bookId") Long bookId) {
        return bookService.getBookById(bookId);
    }

    @RequestMapping(value = "/api/book", method = RequestMethod.GET)
    public List<Book> getBooksByTypeName(@RequestParam(value = "type") String typeName) {
        log.info("type {}", typeName);
        return bookService.getBookByTypeName(typeName);
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
}
