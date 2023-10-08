package com.mainservice.repository;

import com.mainservice.entity.Book;
import com.mainservice.entity.Order;
import com.mainservice.entity.OrderItem;
import com.mainservice.entity.User;
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
