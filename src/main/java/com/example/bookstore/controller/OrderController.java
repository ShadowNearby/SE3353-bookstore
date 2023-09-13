package com.example.bookstore.controller;

import com.example.bookstore.entity.Order;
import com.example.bookstore.service.OrderService;
import com.example.bookstore.util.SessionUtil;
import com.example.bookstore.util.request.AddOrderForm;
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

import java.util.*;


@RestController
@Transactional
public class OrderController {
    private final KafkaTemplate<String, AddOrderForm> kafkaTemplate;

    private final OrderService orderService;

    private final Logger log = LoggerFactory.getLogger(OrderController.class);
    public OrderController(KafkaTemplate<String, AddOrderForm> kafkaTemplate, OrderService orderService) {
        this.kafkaTemplate = kafkaTemplate;
        this.orderService = orderService;
    }

    //    @RequestMapping(value = "/api/order/add", method = RequestMethod.POST)
    //    public void addOrder(@RequestBody @NotNull AddOrderForm addOrderForm) {
    //        orderService.addOrder(addOrderForm);
    //    }

    @RequestMapping(value = "/api/order/add", method = RequestMethod.POST)
    public void addOrder(@RequestBody @NotNull AddOrderForm addOrderForm) {
        Long userId = SessionUtil.getUserId();
        addOrderForm.setUserId(userId);
        kafkaTemplate.send("test", userId.toString(), addOrderForm);
        log.info("send order to kafka {}", addOrderForm);
    }
    @KafkaListener(topics = "test")
    public void receiveAddOrder(@NotNull ConsumerRecord<String, AddOrderForm> consumerRecord) {
        log.info("receive order from kafka {}",consumerRecord.value().toString());
        orderService.addOrder(consumerRecord.value());
    }
    @RequestMapping(value = "/api/order", method = RequestMethod.GET)
    public Set<Order> getOrder() {
        return orderService.getOrderByUserId(SessionUtil.getUserId());
    }




}
