package com.example.demo.service;

import com.example.demo.entity.Order;
import com.example.demo.request.AddOrderForm;

import java.util.Set;

public interface OrderService {
    Set<Order> getOrderByUserId(Long userId);

    void addOrder(AddOrderForm addOrderForm);
}
