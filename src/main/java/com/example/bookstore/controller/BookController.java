package com.example.bookstore.controller;

import com.example.bookstore.entity.Book;
import com.example.bookstore.service.BookService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
public class BookController {

    private final BookService bookService;


    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

//    @RequestMapping(value = "/api/book", method = RequestMethod.GET, params = {"name"})
//    public Book getBookByName(@RequestParam("name") String name) {
//        return bookService.getBookByName(name);
//    }

    @RequestMapping(value = "/api/book/{bookId}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3000")
    public Book getBookById(@PathVariable("bookId") Long bookId) {
        return bookService.getBookById(bookId);
    }

    @RequestMapping(value = "/api/books", method = RequestMethod.GET, params = {"tag_names"})
    public List<Book> getBooksByTagNames(@RequestParam("tag_names") List<String> tagNames) {
        return bookService.getBooksByTagNames(tagNames);
    }

    @RequestMapping(value = "/api/books", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Book> getAllBooks() {
        return bookService.getBooks();
    }

    @RequestMapping(value = "/api/book/add", method = RequestMethod.POST)
    public void addBook(@RequestParam("name") String name, @RequestParam("image") String image,
                        @RequestParam("desc") String desc, @RequestParam("author") String author,
                        @RequestParam("isbn") String isbn, @RequestParam("price") Double price,
                        @RequestParam("inventory") Integer inventory, @RequestParam("tagNames") String tagName
    ) {
        bookService.addBook(name, image, desc, author, isbn, price, inventory, tagName);
    }

    @RequestMapping(value = "/api/book/addinv", method = RequestMethod.POST)
    public Integer addInventory(@RequestParam("name") String name, @RequestParam("count") Integer count) {
        return bookService.addInventory(name, count);
    }

    @RequestMapping(value = "/api/book/subinv", method = RequestMethod.POST)
    public Integer subInventory(@RequestParam("name") String name, @RequestParam("count") Integer count) {
        return bookService.addInventory(name, count);
    }

}
