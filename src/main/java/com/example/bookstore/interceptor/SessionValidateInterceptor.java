package com.example.bookstore.interceptor;

import com.example.bookstore.constant.Constant;
import com.example.bookstore.util.Message;
import com.example.bookstore.util.SessionUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class SessionValidateInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object obj) throws Exception {

        String status = SessionUtil.checkAuth();
//        boolean api = request.getRequestURI().matches("/api/*");
        boolean admin = request.getRequestURI().matches("/admin/*");
        boolean assets = request.getRequestURI().matches("/assets/*");
        if (assets)
            return true;
        if (Objects.equals(status, Constant.NO_USER) || admin && Objects.equals(status, Constant.USER)) {
//            JSONObject object = new JSONObject();
            response.setStatus(401);
            return false;
        }
        return true;
    }

    private void sendJsonBack(HttpServletResponse response, Message message) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.print(JSONObject.fromObject(message));
        } catch (IOException e) {
            System.out.println("send json back error");
        }
    }
}
