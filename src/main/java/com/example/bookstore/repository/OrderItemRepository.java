package com.example.bookstore.repository;

import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.OrderItem;
import com.example.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Set<OrderItem> getOrderItemByOrder(Order order);

    Set<OrderItem> getAllByUser(User user);

    Set<OrderItem> getAllByUserAndOrder(User user, Order order);

    Set<OrderItem> getAllByOrderIn(Set<Order> orders);

    Set<OrderItem> getAllByIdIn(Set<Long> ids);

    Set<OrderItem> getAllByUserAndBook(User user, Book book);

    void deleteOrderItemById(Long id);
}
