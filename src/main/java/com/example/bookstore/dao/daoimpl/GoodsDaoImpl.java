package com.example.bookstore.dao.daoimpl;

import com.example.bookstore.dao.GoodsDao;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Goods;
import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.User;
import com.example.bookstore.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class GoodsDaoImpl implements GoodsDao {
    @Autowired
    private GoodsRepository goodsRepository;

//    public GoodsDaoImpl(GoodsRepository goodsRepository) {
//        this.goodsRepository = goodsRepository;
//    }

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
