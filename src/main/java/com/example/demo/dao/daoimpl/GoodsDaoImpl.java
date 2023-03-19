package com.example.demo.dao.daoimpl;

import com.example.demo.dao.GoodsDao;
import com.example.demo.entity.Goods;
import com.example.demo.repository.GoodsRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
