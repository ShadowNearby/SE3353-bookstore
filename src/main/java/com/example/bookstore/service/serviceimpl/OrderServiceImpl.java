package com.example.bookstore.service.serviceimpl;

import com.example.bookstore.dao.BookDao;
import com.example.bookstore.dao.GoodsDao;
import com.example.bookstore.dao.OrderDao;
import com.example.bookstore.dao.UserDao;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Goods;
import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.User;
import com.example.bookstore.service.OrderService;
import com.example.bookstore.util.SessionUtil;
import com.example.bookstore.util.request.AddOrderForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private BookDao bookDao;

//    public OrderServiceImpl(OrderDao orderDao, UserDao userDao, GoodsDao goodsDao, BookDao bookDao) {
//        this.orderDao = orderDao;
//        this.userDao = userDao;
//        this.goodsDao = goodsDao;
//        this.bookDao = bookDao;
//    }

    @Override
    public Set<Order> getOrderByUserId(Long userId) {
        return orderDao.getOrdersByUserId(userId);
    }

    @Override
    public void addOrder(AddOrderForm addOrderForm) {
        Long userId = SessionUtil.getUserId();
        User user = userDao.getUserById(userId);
        Set<Goods> goodsSet = goodsDao.getGoodsByIds(addOrderForm.getGoodsIds());
        Order order = orderDao.addOrder(new Order(user, goodsSet));
        List<Goods> goodsList = new ArrayList<>(goodsSet);
        for (Goods goods : goodsList) {
            goods.setOrder(order);
            Book book = goods.getBook();
            book.setInventory(book.getInventory() - goods.getCount());
            bookDao.addBook(book);
            goodsDao.addGoods(goods);
        }
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }

    @Override
    public Set<Order> getOrder() {
        Long userId = SessionUtil.getUserId();
        return orderDao.getOrdersByUserId(userId);
    }
}
