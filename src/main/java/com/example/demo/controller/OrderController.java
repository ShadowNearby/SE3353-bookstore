package com.example.demo.controller;

import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;
import com.example.demo.util.request.AddOrderForm;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@Transactional
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/api/order/user/{userId}", method = RequestMethod.GET)
    public Set<Order> getOrderByUserId(@PathVariable Long userId) {
        return orderService.getOrderByUserId(userId);
    }

    @RequestMapping(value = "/api/order/add", method = RequestMethod.POST)
    public void getOrderByUserId(@RequestBody AddOrderForm addOrderForm) {
        orderService.addOrder(addOrderForm);
    }
}
