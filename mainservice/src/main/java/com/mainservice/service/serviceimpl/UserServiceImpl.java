package com.mainservice.service.serviceimpl;

import com.alibaba.fastjson2.JSONObject;
import com.mainservice.constant.Constant;
import com.mainservice.dao.OrderDao;
import com.mainservice.dao.UserDao;
import com.mainservice.entity.Order;
import com.mainservice.entity.OrderItem;
import com.mainservice.entity.User;
import com.mainservice.entity.UserAuth;
import com.mainservice.service.UserService;
import com.mainservice.util.SessionUtil;
import com.mainservice.util.request.*;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final OrderDao orderDao;


    public UserServiceImpl(UserDao userDao, OrderDao orderDao) {
        this.userDao = userDao;
        this.orderDao = orderDao;
    }

    @Override
    public User handleLogin(String username, String password) {
        UserAuth userAuth = userDao.getUserAuthByUsername(username);
        if (userAuth == null)
            return null;
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!Objects.equals(userAuth.getPassword(), md5Password))
            return null;
        return userDao.getUserByUsername(username);
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
        Date data = new Date();
        String avatar = Constant.DEFAULT_AVATAR;
        String password = DigestUtils.md5DigestAsHex(registerForm.getPassword().getBytes());
        String userRole = Constant.USER;
        User user = new User(registerForm.getUsername(), registerForm.getEmail(), userRole, data, avatar);
        UserAuth userAuth = new UserAuth(password, user);
        userDao.updateUser(user, userAuth);
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
        var user_auth = user.get().getUserAuth();
        user_auth.setPassword(password);
        user.get().getUserAuth().setPassword(password);
        userDao.updateUser(user.get(), user_auth);
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
        userDao.updateUser(user, null);
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
            Set<OrderItem> orderItemList = order.getOrderItemList();
            double spend = 0.0;
            if (spendMap.containsKey(username))
                spend = spendMap.get(username);
            for (OrderItem orderItem : orderItemList) {
                spend += orderItem.getBook().getPrice() * orderItem.getCount();
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
        var orderList = orderDao.getOrdersByUserId(userId);
        HashMap<String, Integer> nameCountMap = new HashMap<>();
        double spend = 0.0;
        for (Order order : orderList) {
            if (order == null || order.getOrderTime().getTime() < statisticForm.getBeginDate().getTime() || order.getOrderTime().getTime() > statisticForm.getEndDate().getTime())
                continue;
            List<OrderItem> orderItemList = new ArrayList<>(order.getOrderItemList());
            for (OrderItem orderItem : orderItemList) {
                String key = orderItem.getBook().getName();
                spend += orderItem.getBook().getPrice() * orderItem.getCount();
                boolean exist = nameCountMap.containsKey(key);
                if (exist) {
                    Integer value = nameCountMap.get(key);
                    value += orderItem.getCount();
                    nameCountMap.put(key, value);
                } else {
                    nameCountMap.put(key, orderItem.getCount());
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
