package com.example.bookstore.controller;

import com.example.bookstore.entity.User;
import com.example.bookstore.service.BookService;
import com.example.bookstore.service.UserService;
import com.example.bookstore.util.request.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@Transactional
public class AdminController {
    private final BookService bookService;
    private final UserService userService;

    public AdminController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }


    @RequestMapping(value = "/admin/book/add", method = RequestMethod.POST)
    public void addBook(@RequestBody @NotNull AddBookForm addBookForm
    ) {
        bookService.addBook(addBookForm.getName(), addBookForm.getImage(), addBookForm.getDescription(), addBookForm.getAuthor(), addBookForm.getIsbn(), addBookForm.getPrice(), addBookForm.getInventory(), addBookForm.getTagIds());
    }

    @RequestMapping(value = "/admin/book/put", method = RequestMethod.PUT)
    public void putBook(@RequestBody @NotNull PutBookForm putBookForm
    ) {
        bookService.putBook(putBookForm.getId(), putBookForm.getName(), putBookForm.getImage(), putBookForm.getDescription(), putBookForm.getAuthor(), putBookForm.getIsbn(), putBookForm.getPrice(), putBookForm.getInventory(), putBookForm.getTagIds());
    }

    @RequestMapping(value = "/admin/book/delete", method = RequestMethod.DELETE)
    public void deleteBook(@RequestBody @NotNull IdForm idForm
    ) {
        bookService.deleteBook(idForm.getId());
    }

    @RequestMapping(value = "/admin/book/statistics", method = RequestMethod.POST)
    public List<BookStatisticsForm> bookStatistics(@RequestBody @NotNull StatisticForm statisticForm) {
        return bookService.statistics(statisticForm);
    }

    @RequestMapping(value = "/admin/user/statistics", method = RequestMethod.POST)
    public List<UserStatisticsForm> userStatistics(@RequestBody @NotNull StatisticForm statisticForm) {
        return userService.statistics(statisticForm);
    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public Set<User> getAllUsers() {
        return userService.getAllUsers();
    }


}
