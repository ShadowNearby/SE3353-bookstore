package com.example.bookstore.dao;

import com.example.bookstore.entity.Order;

import java.util.List;

public interface OrderDao {
    List<Order> getOrdersByUserId(Long userId);

    Order updateOrder(Order order);

    List<Order> getAllOrders();
}
