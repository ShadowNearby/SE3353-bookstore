package com.mainservice.controller;

import com.alibaba.fastjson2.JSONObject;
import com.mainservice.constant.Constant;
import com.mainservice.entity.User;
import com.mainservice.service.TickerService;
import com.mainservice.service.UserService;
import com.mainservice.util.Message;
import com.mainservice.util.SessionUtil;
import com.mainservice.util.request.ForgetForm;
import com.mainservice.util.request.LoginForm;
import com.mainservice.util.request.RegisterForm;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Transactional
@RestController
public class AuthController {
    private final UserService userService;
    private final TickerService tickerService;


    public AuthController(UserService userService, TickerService tickerService) {
        this.userService = userService;
        this.tickerService = tickerService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JSONObject login(@RequestBody LoginForm loginForm, HttpServletResponse response) {
        User auth = userService.handleLogin(loginForm.getUsername(), loginForm.getPassword());
        JSONObject message = new JSONObject();
        if (auth == null) {
            message.put(Constant.MESSAGE, Constant.LOGIN_ERROR);
            message.put(Constant.STATE, 400);
            response.setStatus(400);
            return message;
        }
        if (auth.getBanned()) {
            message.put(Constant.MESSAGE, Constant.USER_LOGIN_BANNED);
            response.setStatus(400);
            return message;
        }
        message.put(Constant.USER_ID, auth.getId());
        message.put(Constant.USERNAME, auth.getUsername());
        message.put(Constant.USER_TYPE, auth.getRole());
        SessionUtil.setSession(message);
        message.put(Constant.USER_AVATAR, auth.getAvatar());
        message.put(Constant.MESSAGE, Constant.LOGIN_SUCCESS);
        message.put(Constant.STATE, 200);
//        var tickerService = context.getBean(TickerService.class);
        tickerService.Begin();
        return message;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Message> handleRegister(@RequestBody @NotNull RegisterForm registerForm) {
        String body = userService.handleRegister(registerForm);
        if (!Objects.equals(body, "OK"))
            return ResponseEntity.status(400).body(new Message(body));
        return ResponseEntity.status(200).body(new Message("注册成功"));
    }

    @RequestMapping(value = "/forget", method = RequestMethod.POST)
    public ResponseEntity<Message> handleForget(@RequestBody @NotNull ForgetForm forgetForm) {
        String body = userService.handleForget(forgetForm);
        if (!Objects.equals(body, "OK"))
            return ResponseEntity.status(400).body(new Message(body));
        return ResponseEntity.status(200).body(new Message("修改成功"));
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity<Message> handleLogout() {
//        var tickerService = context.getBean(TickerService.class);
        var message = new Message(String.format("%s秒", tickerService.End()));
        SessionUtil.removeSession();
        return ResponseEntity.status(200).body(message);
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public JSONObject handleCheck() {
        String auth = SessionUtil.checkAuth();
        JSONObject object = new JSONObject();
        if (auth == null || Objects.equals(auth, Constant.NO_USER)) {
            object.put("auth", false);
            return object;
        }
        object.put("auth", true);
        return object;
    }
}
