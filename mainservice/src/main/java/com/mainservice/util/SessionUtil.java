package com.mainservice.util;

import com.alibaba.fastjson2.JSONObject;
import com.mainservice.constant.Constant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SessionUtil {
    public static String checkAuth() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // Session
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            HttpSession session = request.getSession(false);
            if (session != null) {
                return (String) session.getAttribute(Constant.USER_TYPE);
            }
        }
        return Constant.NO_USER;
    }

    public static void setSession(JSONObject data) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // Session
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            HttpSession session = request.getSession();
            for (String str : data.keySet()) {
                Object val = data.get(str);
                session.setAttribute(str, val);
            }
        }
    }

    public static void removeSession() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        // Session
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            HttpSession session = request.getSession(false);

            if (session != null) {
                session.invalidate();
            }
        }
    }

    public static Long getUserId() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();
        HttpSession session = request.getSession(false);
        return (Long) session.getAttribute("userId");
    }
}
