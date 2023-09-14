package com.example.bookstore.controller;

import com.example.bookstore.entity.Order;
import com.example.bookstore.service.OrderService;
import com.example.bookstore.util.SessionUtil;
import com.example.bookstore.util.request.AddOrderForm;
import com.example.bookstore.websocket.WebSocketServer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.Set;


@RestController
@Transactional
public class OrderController {
    private final KafkaTemplate<String, AddOrderForm> kafkaTemplate;

    private final WebApplicationContext applicationContext;
    private final WebSocketServer webSocketServer;
    private final Logger log = LoggerFactory.getLogger(OrderController.class);

    public OrderController(KafkaTemplate<String, AddOrderForm> kafkaTemplate, WebApplicationContext applicationContext, WebSocketServer webSocketServer) {
        this.kafkaTemplate = kafkaTemplate;
        this.applicationContext = applicationContext;
        this.webSocketServer = webSocketServer;
    }

    @RequestMapping(value = "/api/order/add", method = RequestMethod.POST)
    public void addOrder(@RequestBody @NotNull AddOrderForm addOrderForm) {
        Long userId = SessionUtil.getUserId();
        addOrderForm.setUserId(userId);
        kafkaTemplate.send("test", userId.toString(), addOrderForm);
        log.info("send order to kafka {}", addOrderForm);
    }

    @KafkaListener(topics = "test")
    public void receiveAddOrder(@NotNull ConsumerRecord<String, AddOrderForm> consumerRecord) throws IOException {
        log.info("receive order from kafka {}", consumerRecord.value().toString());
        AddOrderForm addOrderForm = consumerRecord.value();
        applicationContext.getBean(OrderService.class).addOrder(addOrderForm);
        webSocketServer.sendMessageTo("订单处理成功", addOrderForm.getUserId());
    }

    @RequestMapping(value = "/api/order", method = RequestMethod.GET)
    public Set<Order> getOrder() {
        return applicationContext.getBean(OrderService.class).getOrderByUserId(SessionUtil.getUserId());
    }

}
