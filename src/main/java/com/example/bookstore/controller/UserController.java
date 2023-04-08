package com.example.bookstore.controller;

import com.example.bookstore.entity.User;
import com.example.bookstore.service.UserService;
import com.example.bookstore.util.Message;
import com.example.bookstore.util.request.ForgetForm;
import com.example.bookstore.util.request.RegisterForm;
import com.example.bookstore.util.request.UserPutForm;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Set;

@RestController
@Transactional
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/api/users", method = RequestMethod.GET)
    public Set<User> getAllUsers() {
        return userService.getAllUsers();
    }


    @RequestMapping(value = "/api/user/put", method = RequestMethod.PUT)
    public ResponseEntity<Message> handleRegister(@RequestBody @NotNull UserPutForm userPutForm) {
        String body = userService.handleUserPut(userPutForm);
        if (!Objects.equals(body, "OK"))
            return ResponseEntity.status(400).body(new Message(body));
        return ResponseEntity.status(200).body(new Message("修改成功"));
    }

    @RequestMapping(value = "/api/user/add", method = RequestMethod.POST)
    public ResponseEntity<Message> handleRegister(@RequestBody @NotNull RegisterForm registerForm) {
        String body = userService.handleRegister(registerForm);
        if (!Objects.equals(body, "OK"))
            return ResponseEntity.status(400).body(new Message(body));
        return ResponseEntity.status(200).body(new Message("注册成功"));
    }

    @RequestMapping(value = "/api/user/forget", method = RequestMethod.POST)
    public ResponseEntity<Message> handleForget(@RequestBody @NotNull ForgetForm forgetForm) {
        String body = userService.handleForget(forgetForm);
        if (!Objects.equals(body, "OK"))
            return ResponseEntity.status(400).body(new Message(body));
        return ResponseEntity.status(200).body(new Message("修改成功"));
    }

    @RequestMapping(value = "/api/user/{account}", method = RequestMethod.GET)
    public User getUserByAccount(@PathVariable("account") String account) {
        return userService.getUserByAccount(account);
    }
}
