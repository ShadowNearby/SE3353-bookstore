package com.example.demo.dao.daoimpl;

import com.example.demo.dao.GoodsDao;
import com.example.demo.entity.Book;
import com.example.demo.entity.Goods;
import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import com.example.demo.repository.GoodsRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class GoodsDaoImpl implements GoodsDao {
    private final GoodsRepository goodsRepository;

    public GoodsDaoImpl(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    @Override
    public List<Goods> getAllGoods() {
        return goodsRepository.findAll();
    }

    @Override
    public Set<Goods> findGoodsInCart(User user, Book book) {
        return goodsRepository.findAllByUserAndBook(user, book);
    }

    @Override
    public Set<Goods> getGoodsByOrder(Order order) {
        return goodsRepository.getGoodsByOrder(order);
    }

    @Override
    public Set<Goods> getGoodsByUser(User user) {
        return goodsRepository.getAllByUser(user);
    }


    @Override
    public Set<Goods> getGoodsByOrders(Set<Order> orders) {
        return goodsRepository.getAllByOrderIn(orders);
    }

    @Override
    public Set<Goods> getGoodsByIds(Set<Long> ids) {
        return goodsRepository.getAllByIdIn(ids);
    }

    @Override
    public Goods addGoods(Goods goods) {
        return goodsRepository.save(goods);
    }


    @Override
    public void removeById(Long id) {
        goodsRepository.deleteGoodsById(id);
    }
}
