package com.example.demo.service;

import com.example.demo.entity.Goods;
import com.example.demo.request.AddGoodsForm;
import com.example.demo.request.IdForm;

import java.util.List;
import java.util.Set;

public interface GoodsService {
    List<Goods> getAllGoods();

    Set<Goods> getGoodsInCart(Long userId);

    Set<Goods> getGoodsInOrder(Long userId);

    Long addGoodsByBookIdUserId(AddGoodsForm addGoodsForm);

    void removeById(IdForm idForm);
}
