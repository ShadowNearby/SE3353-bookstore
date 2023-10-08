package com.mainservice.controller;

import com.alibaba.fastjson2.JSONObject;
import com.mainservice.entity.User;
import com.mainservice.service.UserService;
import com.mainservice.util.Message;
import com.mainservice.util.request.StatisticForm;
import com.mainservice.util.request.UserPutForm;
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
