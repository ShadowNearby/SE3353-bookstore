package com.example.bookstore.service;

import com.example.bookstore.entity.Order;
import com.example.bookstore.util.request.AddOrderForm;

import java.util.List;
import java.util.Set;

public interface OrderService {
    Set<Order> getOrderByUserId(Long userId);

    void addOrder(AddOrderForm addOrderForm);

    List<Order> getAllOrders();

    Set<Order> getOrder();
}
