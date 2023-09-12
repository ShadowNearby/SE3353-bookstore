package com.example.bookstore.controller;

import com.example.bookstore.entity.Order;
import com.example.bookstore.service.OrderService;
import com.example.bookstore.util.SessionUtil;
import com.example.bookstore.util.request.AddOrderForm;
import org.jetbrains.annotations.NotNull;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@Transactional
public class OrderController {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final OrderService orderService;

    public OrderController(KafkaTemplate<String, Object> kafkaTemplate, OrderService orderService) {
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
        kafkaTemplate.send("test", userId.toString(), addOrderForm);
    }

//    @KafkaListener(topics = {"test"}, concurrency = "12")
//    public void receiveOrder(Message message) {
//        System.out.println(message.toString());
//    }

    @RequestMapping(value = "/api/order", method = RequestMethod.GET)
    public Set<Order> getOrder() {
        return orderService.getOrder();
    }
}
