package com.example.bookstore.service;

import com.example.bookstore.entity.Order;
import com.example.bookstore.util.request.AddOrderForm;

import java.util.List;

public interface OrderService {
    List<Order> getOrdersByUserId(Long userId);

    void updateOrder(AddOrderForm addOrderForm);

    List<Order> getAllOrders();

    List<Order> getOrders();
}
