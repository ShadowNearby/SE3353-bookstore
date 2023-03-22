package com.example.demo.repository;

import com.example.demo.entity.Book;
import com.example.demo.entity.Goods;
import com.example.demo.entity.Order;
import com.example.demo.entity.User;
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
