package com.example.bookstore.controller;

import com.example.bookstore.constant.Constant;
import com.example.bookstore.entity.User;
import com.example.bookstore.service.UserService;
import com.example.bookstore.util.Message;
import com.example.bookstore.util.SessionUtil;
import com.example.bookstore.util.request.ForgetForm;
import com.example.bookstore.util.request.LoginForm;
import com.example.bookstore.util.request.RegisterForm;
import jakarta.servlet.http.HttpServletResponse;
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
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
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
    public void handleLogout() {
        SessionUtil.removeSession();
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public JSONObject handleCheck() {
        String auth = SessionUtil.checkAuth();
        JSONObject object = new JSONObject();
        if (Objects.equals(auth, Constant.NO_USER)) {
            object.put("auth", false);
            return object;
        }
        object.put("auth", true);
        return object;
    }
}
