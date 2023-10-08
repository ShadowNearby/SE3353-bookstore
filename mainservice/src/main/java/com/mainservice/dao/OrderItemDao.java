package com.mainservice.dao;

import com.mainservice.entity.Book;
import com.mainservice.entity.Order;
import com.mainservice.entity.OrderItem;
import com.mainservice.entity.User;

import java.util.List;
import java.util.Set;

public interface OrderItemDao {
    List<OrderItem> getAllOrderItem();

    Set<OrderItem> getOrderItemInCart(User user, Book book);

    Set<OrderItem> getOrderItemByUser(User user);

    Set<OrderItem> getOrderItemByOrders(Set<Order> orders);

    Set<OrderItem> getOrderItemByIds(Set<Long> ids);

    OrderItem updateOrderItem(OrderItem goods);

    void removeById(Long id);
}
