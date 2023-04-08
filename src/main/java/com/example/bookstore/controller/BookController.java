package com.example.bookstore.controller;

import com.example.bookstore.entity.Book;
import com.example.bookstore.service.BookService;
import com.example.bookstore.util.request.AddBookForm;
import com.example.bookstore.util.request.BookStatisticsForm;
import com.example.bookstore.util.request.IdForm;
import com.example.bookstore.util.request.PutBookForm;
import org.jetbrains.annotations.NotNull;
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
    public void addBook(@RequestBody @NotNull AddBookForm addBookForm
    ) {
        bookService.addBook(addBookForm.getName(), addBookForm.getImage(), addBookForm.getDescription(), addBookForm.getAuthor(), addBookForm.getIsbn(), addBookForm.getPrice(), addBookForm.getInventory(), addBookForm.getTagIds());
    }

    @RequestMapping(value = "/api/book/put", method = RequestMethod.PUT)
    public void putBook(@RequestBody @NotNull PutBookForm putBookForm
    ) {
        bookService.putBook(putBookForm.getId(), putBookForm.getName(), putBookForm.getImage(), putBookForm.getDescription(), putBookForm.getAuthor(), putBookForm.getIsbn(), putBookForm.getPrice(), putBookForm.getInventory(), putBookForm.getTagIds());
    }

    @RequestMapping(value = "/api/book/delete", method = RequestMethod.DELETE)
    public void deleteBook(@RequestBody @NotNull IdForm idForm
    ) {
        bookService.deleteBook(idForm.getId());
    }

    @RequestMapping(value = "/api/book/addinv", method = RequestMethod.POST)
    public Integer addInventory(@RequestParam("name") String name, @RequestParam("count") Integer count) {
        return bookService.addInventory(name, count);
    }

    @RequestMapping(value = "/api/book/subinv", method = RequestMethod.POST)
    public Integer subInventory(@RequestParam("name") String name, @RequestParam("count") Integer count) {
        return bookService.addInventory(name, count);
    }

    @RequestMapping(value = "/api/book/statistics", method = RequestMethod.GET)
    public List<BookStatisticsForm> bookStatistics() {
        return bookService.statistics();
    }
}
