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
import com.example.bookstore.util.request.AddOrderForm;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public void addOrder(@NotNull AddOrderForm addOrderForm) {
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

    @Override
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }
}
