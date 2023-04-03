package com.example.bookstore.repository;

import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Goods;
import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface GoodsRepository extends JpaRepository<Goods, Long> {
    Set<Goods> getGoodsByOrder(Order order);

    Set<Goods> getAllByUser(User user);

    Set<Goods> getAllByUserAndOrder(User user, Order order);

    Set<Goods> getAllByOrderIn(Set<Order> orders);

    Set<Goods> getAllByIdIn(Set<Long> ids);

    Set<Goods> findAllByUserAndBook(User user, Book book);

    void deleteGoodsById(Long id);

    void deleteById(Long id);
}
