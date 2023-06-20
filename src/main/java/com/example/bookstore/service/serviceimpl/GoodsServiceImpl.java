package com.example.bookstore.service.serviceimpl;

import com.example.bookstore.dao.BookDao;
import com.example.bookstore.dao.GoodsDao;
import com.example.bookstore.dao.OrderDao;
import com.example.bookstore.dao.UserDao;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Goods;
import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.User;
import com.example.bookstore.service.GoodsService;
import com.example.bookstore.util.SessionUtil;
import com.example.bookstore.util.request.AddGoodsForm;
import com.example.bookstore.util.request.IdForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private BookDao bookDao;

//    public GoodsServiceImpl(GoodsDao goodsDao, UserDao userDao, OrderDao orderDao, BookDao bookDao) {
//        this.goodsDao = goodsDao;
//        this.userDao = userDao;
//        this.orderDao = orderDao;
//        this.bookDao = bookDao;
//    }

    @Override
    public List<Goods> getAllGoods() {
        return goodsDao.getAllGoods();
    }

    @Override
    public Set<Goods> getGoodsInCart() {
        Long userId = SessionUtil.getUserId();
        User user = userDao.getUserById(userId);
        Set<Goods> allGoods = goodsDao.getGoodsByUser(user);
        allGoods.removeIf(goods -> goods.getOrder() != null || goods.getBook().getDeleted());
        return allGoods;
    }

    @Override
    public Set<Goods> getGoodsInOrder() {
        Long userId = SessionUtil.getUserId();
        User user = userDao.getUserById(userId);
        Set<Order> orders = orderDao.getOrderByUser(user);
        return goodsDao.getGoodsByOrders(orders);
    }

    @Override
    public Long addGoodsByBookId(AddGoodsForm addGoodsForm) {
        Long userId = SessionUtil.getUserId();
        User user = userDao.getUserById(userId);
        Book book = bookDao.getBookById(addGoodsForm.getBookId());
        Set<Goods> goods = goodsDao.findGoodsInCart(user, book);
        for (Goods good : goods) {
            if (good.getOrder() == null) {
                good.setCount(good.getCount() + addGoodsForm.getCount());
                goodsDao.addGoods(good);
                return good.getId();
            }
        }
        return goodsDao.addGoods(new Goods(addGoodsForm.getCount(), book, user)).getId();
    }

    @Override
    public void removeById(IdForm idForm) {
        goodsDao.removeById(idForm.getId());
    }
}

