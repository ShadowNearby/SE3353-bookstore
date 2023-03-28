package com.example.demo.service.serviceimpl;

import com.example.demo.dao.BookDao;
import com.example.demo.dao.GoodsDao;
import com.example.demo.dao.OrderDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Book;
import com.example.demo.entity.Goods;
import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import com.example.demo.service.OrderService;
import com.example.demo.util.request.AddOrderForm;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final UserDao userDao;
    private final GoodsDao goodsDao;
    private final BookDao bookDao;

    public OrderServiceImpl(OrderDao orderDao, UserDao userDao, GoodsDao goodsDao, BookDao bookDao) {
        this.orderDao = orderDao;
        this.userDao = userDao;
        this.goodsDao = goodsDao;
        this.bookDao = bookDao;
    }

    @Override
    public Set<Order> getOrderByUserId(Long userId) {
        return orderDao.getOrdersByUserId(userId);
    }

    @Override
    public void addOrder(AddOrderForm addOrderForm) {
        User user = userDao.getUserById(addOrderForm.getUserId());
        Set<Goods> goods = goodsDao.getGoodsByIds(addOrderForm.getGoodsIds());
        Order order = orderDao.addOrder(new Order(user, goods));
        for (Goods good : goods) {
            good.setOrder(order);
            Book book = good.getBook();
            book.setInventory(book.getInventory() - good.getCount());
            bookDao.addBook(book);
            goodsDao.addGoods(good);
        }
    }
}
