package com.mainservice.dao;

import com.mainservice.entity.Order;

import java.util.List;

public interface OrderDao {
    List<Order> getOrdersByUserId(Long userId);

    Order updateOrder(Order order);

    List<Order> getAllOrders();
}
