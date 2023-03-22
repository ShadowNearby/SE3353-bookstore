package com.example.demo.dao;

import com.example.demo.entity.Book;
import com.example.demo.entity.Goods;
import com.example.demo.entity.Order;
import com.example.demo.entity.User;

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
