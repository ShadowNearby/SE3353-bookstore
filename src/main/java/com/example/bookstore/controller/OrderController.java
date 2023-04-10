package com.example.bookstore.controller;

import com.example.bookstore.entity.Order;
import com.example.bookstore.service.OrderService;
import com.example.bookstore.util.request.AddOrderForm;
import org.jetbrains.annotations.NotNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@Transactional
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @RequestMapping(value = "/api/order/add", method = RequestMethod.POST)
    public void addOrder(@RequestBody @NotNull AddOrderForm addOrderForm) {
        orderService.addOrder(addOrderForm);
    }

    @RequestMapping(value = "/api/order", method = RequestMethod.GET)
    public Set<Order> getOrder() {
        return orderService.getOrder();
    }
}
