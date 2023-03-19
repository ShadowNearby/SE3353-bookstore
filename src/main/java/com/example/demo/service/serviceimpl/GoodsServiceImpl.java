package com.example.demo.service.serviceimpl;

import com.example.demo.dao.GoodsDao;
import com.example.demo.entity.Goods;
import com.example.demo.service.GoodsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    private final GoodsDao goodsDao;

    public GoodsServiceImpl(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    @Override
    public List<Goods> getAllGoods() {
        return goodsDao.getAllGoods();
    }
}

