package com.example.bookstore.dao;

import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Goods;
import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.User;

import java.util.List;
import java.util.Set;

public interface GoodsDao {
    List<Goods> getAllGoods();

    Set<Goods> findGoodsInCart(User user, Book book);

    Set<Goods> getGoodsByOrder(Order order);

    Set<Goods> getGoodsByUser(User user);

    Set<Goods> getGoodsByOrders(Set<Order> orders);

    Set<Goods> getGoodsByIds(Set<Long> ids);

    Goods addGoods(Goods goods);

    void removeById(Long id);
}
