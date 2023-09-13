package com.example.bookstore.controller;

import com.example.bookstore.entity.User;
import com.example.bookstore.service.UserService;
import com.example.bookstore.util.Message;
import com.example.bookstore.util.request.StatisticForm;
import com.example.bookstore.util.request.UserPutForm;
import net.sf.json.JSONObject;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@Transactional
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/api/user/put", method = RequestMethod.PUT)
    public ResponseEntity<Message> handleRegister(@RequestBody @NotNull UserPutForm userPutForm) {
        String body = userService.handleUserPut(userPutForm);
        if (!Objects.equals(body, "OK"))
            return ResponseEntity.status(400).body(new Message(body));
        return ResponseEntity.status(200).body(new Message("修改成功"));
    }

    @RequestMapping(value = "/api/user", method = RequestMethod.GET)
    public User getUser() {
        return userService.getUser();
    }

    @RequestMapping(value = "/api/user/statistics", method = RequestMethod.POST)
    public JSONObject userPersonalStatistics(@RequestBody @NotNull StatisticForm statisticForm) {
        return userService.statisticsPersonal(statisticForm);
    }
}
