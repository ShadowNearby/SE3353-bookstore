package com.example.bookstore.service.serviceimpl;

import com.example.bookstore.dao.OrderDao;
import com.example.bookstore.dao.UserDao;
import com.example.bookstore.entity.Goods;
import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.User;
import com.example.bookstore.service.UserService;
import com.example.bookstore.util.SessionUtil;
import com.example.bookstore.util.request.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private OrderDao orderDao;


//    public UserServiceImpl(UserDao userDao, OrderDao orderDao) {
//        this.userDao = userDao;
//        this.orderDao = orderDao;
//    }

    @Override
    public User handleLogin(String username, String password) {
        Optional<User> user = userDao.findUserByUsername(username);
        if (user.isEmpty())
            return null;
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!Objects.equals(user.get().getUserAuth().getPassword(), md5Password))
            return null;
        return user.get();
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public String handleRegister(RegisterForm registerForm) {
        if (userDao.findUserByUsername(registerForm.getUsername()).isPresent())
            return "该账户已存在";
        if (userDao.findUserByEmail(registerForm.getEmail()).isPresent())
            return "该邮箱已存在";
        userDao.addUser(registerForm);
        return "OK";
    }

    @Override
    public String handleForget(ForgetForm forgetForm) {
        Optional<User> user = userDao.findUserByUsername(forgetForm.getUsername());
        if (user.isEmpty())
            return "账户不存在";
        if (!Objects.equals(user.get().getEmail(), forgetForm.getEmail()))
            return "账户对应邮箱不正确";
        String password = DigestUtils.md5DigestAsHex(forgetForm.getPassword().getBytes());
        user.get().getUserAuth().setPassword(password);
        userDao.updateUser(user.get());
        return "OK";
    }

    @Override
    public Set<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public String handleUserPut(UserPutForm userPutForm) {
        User user = userDao.getUserById(userPutForm.getId());
        if (!Objects.equals(user.getUsername(), userPutForm.getUsername()) && userDao.findUserByUsername(userPutForm.getUsername()).isPresent())
            return "该账户已存在";
        if (!Objects.equals(user.getEmail(), userPutForm.getEmail()) && userDao.findUserByEmail(userPutForm.getEmail()).isPresent())
            return "该邮箱已存在";
        user.setAvatar(userPutForm.getAvatar());
        user.setBanned(userPutForm.getBanned());
        user.setEmail(userPutForm.getEmail());
        user.setUsername(userPutForm.getUsername());
        user.setRegisterTime(userPutForm.getRegisterTime());
        user.setRole(userPutForm.getRole());
        userDao.updateUser(user);
        return "OK";
    }

    @Override
    public List<UserStatisticsForm> statisticsAll(StatisticForm statisticForm) {
        HashMap<String, Double> spendMap = new HashMap<>();
        List<Order> orderList = orderDao.getAllOrders();
        for (Order order : orderList) {
            Date orderTime = order.getOrderTime();
            if (orderTime.getTime() < statisticForm.getBeginDate().getTime() || orderTime.getTime() > statisticForm.getEndDate().getTime())
                continue;
            String username = order.getUser().getUsername();
            Set<Goods> goodsList = order.getGoodsList();
            double spend = 0.0;
            if (spendMap.containsKey(username))
                spend = spendMap.get(username);
            for (Goods goods : goodsList) {
                spend += goods.getBook().getPrice() * goods.getCount();
            }
            spendMap.put(username, spend);
        }
        List<UserStatisticsForm> userStatisticsForms = new ArrayList<>();
        for (String key : spendMap.keySet()) {
            userStatisticsForms.add(new UserStatisticsForm(key, spendMap.get(key)));
        }
        userStatisticsForms.sort((o1, o2) -> o2.getSpend().compareTo(o1.getSpend()));
        return userStatisticsForms;
    }

    @Override
    public User getUser() {
        Long userId = SessionUtil.getUserId();
        return userDao.getUserById(userId);
    }

    @Override
    public JSONObject statisticsPersonal(StatisticForm statisticForm) {
        Long userId = SessionUtil.getUserId();
        Set<Order> orderSet = orderDao.getOrdersByUserId(userId);
        List<Order> orderList = new ArrayList<>(orderSet);
        HashMap<String, Integer> nameCountMap = new HashMap<>();
        double spend = 0.0;
        for (Order order : orderList) {
            if (order == null || order.getOrderTime().getTime() < statisticForm.getBeginDate().getTime() || order.getOrderTime().getTime() > statisticForm.getEndDate().getTime())
                continue;
            List<Goods> goodsList = new ArrayList<>(order.getGoodsList());
            for (Goods goods : goodsList) {
                String key = goods.getBook().getName();
                spend += goods.getBook().getPrice() * goods.getCount();
                boolean exist = nameCountMap.containsKey(key);
                if (exist) {
                    Integer value = nameCountMap.get(key);
                    value += goods.getCount();
                    nameCountMap.put(key, value);
                } else {
                    nameCountMap.put(key, goods.getCount());
                }
            }
        }
        List<BookStatisticsForm> bookStatisticsForms = new ArrayList<>();
        for (String key : nameCountMap.keySet()) {
            bookStatisticsForms.add(new BookStatisticsForm(key, nameCountMap.get(key)));
        }
        bookStatisticsForms.sort((o1, o2) -> o2.getCount() - o1.getCount());
        JSONObject responseObject = new JSONObject();
        responseObject.put("books", bookStatisticsForms);
        responseObject.put("spend", spend);
        return responseObject;
    }
}
