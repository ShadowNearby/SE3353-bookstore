package com.example.bookstore.dao;

import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.OrderItem;
import com.example.bookstore.entity.User;

import java.util.List;
import java.util.Set;

public interface OrderItemDao {
    List<OrderItem> getAllOrderItem();

    Set<OrderItem> findOrderItemInCart(User user, Book book);

    Set<OrderItem> getOrderItemByUser(User user);

    Set<OrderItem> getOrderItemByOrders(Set<Order> orders);

    Set<OrderItem> getOrderItemByIds(Set<Long> ids);

    OrderItem updateOrderItem(OrderItem goods);

    void removeById(Long id);
}
