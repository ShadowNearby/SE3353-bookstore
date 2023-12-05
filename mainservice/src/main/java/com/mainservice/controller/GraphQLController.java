package com.mainservice.controller;

import com.mainservice.entity.Book;
import com.mainservice.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GraphQLController {
    private final BookService bookService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public GraphQLController(BookService bookService) {
        this.bookService = bookService;
    }

    @QueryMapping
    public List<Book> bookByName(@Argument String name) {
        log.info("book name {}", name);
        var books = new ArrayList<Book>();
        books.add(bookService.getBookByName(name));
        return books;
    }
}
