package com.mainservice.interceptor;

import com.mainservice.constant.Constant;
import com.mainservice.util.SessionUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;

public class SessionValidateInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object obj) {

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

//    private void sendJsonBack(HttpServletResponse response, Message message) {
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json; charset=utf-8");
//        try (PrintWriter writer = response.getWriter()) {
//            writer.print(JSONObject.fromObject(message));
//        } catch (IOException e) {
//            System.out.println("send json back error");
//        }
//    }
}
