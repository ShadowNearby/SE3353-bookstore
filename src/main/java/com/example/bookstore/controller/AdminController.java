package com.example.bookstore.controller;

import com.alibaba.fastjson2.JSONObject;
import com.example.bookstore.constant.Constant;
import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.User;
import com.example.bookstore.service.BookService;
import com.example.bookstore.service.OrderService;
import com.example.bookstore.service.UserService;
import com.example.bookstore.util.SessionUtil;
import com.example.bookstore.util.request.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
@Transactional
public class AdminController {
    private final BookService bookService;
    private final UserService userService;
    private final OrderService orderService;

    public AdminController(BookService bookService, UserService userService, OrderService orderService) {
        this.bookService = bookService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @RequestMapping(value = "/admin/user/{username}", method = RequestMethod.GET)
    public User getUserByUsername(@PathVariable("username") String username) {
        return userService.getUserByUsername(username);
    }

    @RequestMapping(value = "/admin/book/add", method = RequestMethod.POST)
    public void addBook(@RequestBody @NotNull AddBookForm addBookForm
    ) {
        bookService.addBook(addBookForm.getName(), addBookForm.getImage(), addBookForm.getDescription(), addBookForm.getAuthor(), addBookForm.getIsbn(), addBookForm.getPrice(), addBookForm.getInventory());
    }

    @RequestMapping(value = "/admin/book/put", method = RequestMethod.PUT)
    public void putBook(@RequestBody @NotNull PutBookForm putBookForm
    ) {
        bookService.putBook(putBookForm.getId(), putBookForm.getName(), putBookForm.getImage(), putBookForm.getDescription(), putBookForm.getAuthor(), putBookForm.getIsbn(), putBookForm.getPrice(), putBookForm.getInventory());
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
        return userService.statisticsAll(statisticForm);
    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public Set<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/admin/order/user/{userId}", method = RequestMethod.GET)
    public List<Order> getOrderByUserId(@PathVariable Long userId) {
        return orderService.getOrdersByUserId(userId);
    }

    @RequestMapping(value = "/admin/orders", method = RequestMethod.GET)
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @RequestMapping(value = "/admin/check", method = RequestMethod.POST)
    public JSONObject check() {
        String auth = SessionUtil.checkAuth();
        JSONObject object = new JSONObject();
        if (!Objects.equals(auth, Constant.ADMIN)) {
            object.put("auth", false);
            return object;
        }
        object.put("auth", true);
        return object;
    }
}
