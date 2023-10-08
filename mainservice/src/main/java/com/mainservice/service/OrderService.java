package com.mainservice.service;

import com.mainservice.entity.Order;
import com.mainservice.util.request.AddOrderForm;

import java.util.List;

public interface OrderService {
    List<Order> getOrdersByUserId(Long userId);

    void updateOrder(AddOrderForm addOrderForm);

    List<Order> getAllOrders();

    List<Order> getOrders();
}
