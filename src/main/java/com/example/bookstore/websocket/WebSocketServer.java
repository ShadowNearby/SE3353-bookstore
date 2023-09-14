package com.example.bookstore.websocket;

import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint("/order/{userId}")
@Component
public class WebSocketServer {
    private final Logger log = LoggerFactory.getLogger(WebSocketServer.class);
    private static final AtomicInteger onlineCount = new AtomicInteger(0);
    private static final ConcurrentHashMap<Long, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    private Session session;
    private Long userId;

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") Long userId) {
        this.session = session;
        this.userId = userId;
        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            webSocketMap.put(userId, this);
        } else {
            webSocketMap.put(userId, this);
            addOnlineCount();
        }
        log.info("订单socket连接 用户id:" + userId + ",当前在线人数为:" + getOnlineCount());
//        try {
//            sendMessageTo("连接成功！",userId);
//        } catch (IOException e) {
//            log.error("用户:" + userId + ",网络异常!!!!!!");
//        }
    }

    @OnClose
    public void onClose() {
        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            subOnlineCount();
        }
        log.info("订单socket断开 用户id:" + userId + ",当前在线人数为:" + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("用户消息:" + userId + ",报文:" + message);
        if (!message.isEmpty()) {
            try {
                JSONObject jsonObject = JSONObject.fromObject(message);
                jsonObject.put("fromUserId", this.userId);
                Long toUserId = Long.valueOf(jsonObject.getString("toUserId"));
                if (webSocketMap.containsKey(toUserId)) {
                    sendMessageTo(message, toUserId);
                } else {
                    log.error("请求的 userId:" + toUserId + "不在该服务器上");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:" + this.userId + ",原因:" + error.getMessage());
        error.printStackTrace();
    }

    public void sendMessageTo(String message, Long userId) throws IOException {
        if (webSocketMap.containsKey(userId)) {
            log.info("send {} to user {}", message, userId);
            webSocketMap.get(userId).session.getBasicRemote().sendText(message);
        }
    }

    public static synchronized AtomicInteger getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount.getAndIncrement();
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount.getAndDecrement();
    }
}

