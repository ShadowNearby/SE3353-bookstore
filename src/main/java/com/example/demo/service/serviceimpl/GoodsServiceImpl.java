package com.example.demo.service.serviceimpl;

import com.example.demo.dao.BookDao;
import com.example.demo.dao.GoodsDao;
import com.example.demo.dao.OrderDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Book;
import com.example.demo.entity.Goods;
import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import com.example.demo.request.AddGoodsForm;
import com.example.demo.request.IdForm;
import com.example.demo.service.GoodsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class GoodsServiceImpl implements GoodsService {
    private final GoodsDao goodsDao;
    private final UserDao userDao;
    private final OrderDao orderDao;
    private final BookDao bookDao;

    public GoodsServiceImpl(GoodsDao goodsDao, UserDao userDao, OrderDao orderDao, BookDao bookDao) {
        this.goodsDao = goodsDao;
        this.userDao = userDao;
        this.orderDao = orderDao;
        this.bookDao = bookDao;
    }

    @Override
    public List<Goods> getAllGoods() {
        return goodsDao.getAllGoods();
    }

    @Override
    public Set<Goods> getGoodsInCart(Long userId) {
        User user = userDao.getUserById(userId);
        Set<Goods> allGoods = goodsDao.getGoodsByUser(user);
        allGoods.removeIf(goods -> goods.getOrder() != null);
        return allGoods;
    }

    @Override
    public Set<Goods> getGoodsInOrder(Long userId) {
        User user = userDao.getUserById(userId);
        Set<Order> orders = orderDao.getOrderByUser(user);
        return goodsDao.getGoodsByOrders(orders);
    }

    @Override
    public Long addGoodsByBookIdUserId(AddGoodsForm addGoodsForm) {
        User user = userDao.getUserById(addGoodsForm.getUserId());
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

