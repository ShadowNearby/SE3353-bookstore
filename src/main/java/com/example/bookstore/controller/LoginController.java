package com.example.bookstore.controller;

import com.example.bookstore.constant.Constant;
import com.example.bookstore.entity.User;
import com.example.bookstore.service.UserService;
import com.example.bookstore.util.SessionUtil;
import com.example.bookstore.util.request.LoginForm;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JSONObject login(@RequestBody LoginForm loginForm, HttpServletResponse response) {
        User auth = userService.handleLogin(loginForm.getUsername(), loginForm.getPassword());
        JSONObject message = new JSONObject();
        if (auth == null) {
            message.put(Constant.MESSAGE, Constant.LOGIN_ERROR);
            response.setStatus(401);
            return message;
        }
        message.put(Constant.USER_ID, auth.getId());
        message.put(Constant.USERNAME, auth.getUsername());
        message.put(Constant.USER_TYPE, auth.getRole());
        SessionUtil.setSession(message);
        message.put(Constant.USER_AVATAR, auth.getAvatar());
        return message;
    }
}
