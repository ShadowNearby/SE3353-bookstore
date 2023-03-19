package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.entity.User;
import com.example.demo.request.LoginForm;
import com.example.demo.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/api/user", method = RequestMethod.POST)
    public Boolean LoginCheck(@RequestBody @NotNull LoginForm loginForm) {
        return userService.LoginCheck(loginForm.getAccount(), loginForm.getPassword());
    }

    @RequestMapping(value = "/api/user/{account}", method = RequestMethod.GET)
    public User getUserByAccount(@PathVariable("account") String account) {
        return userService.getUserByAccount(account);
    }
}
