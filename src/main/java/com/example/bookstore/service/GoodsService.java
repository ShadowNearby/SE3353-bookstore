package com.example.bookstore.service;

import com.example.bookstore.entity.Goods;
import com.example.bookstore.util.request.AddGoodsForm;
import com.example.bookstore.util.request.IdForm;

import java.util.List;
import java.util.Set;

public interface GoodsService {
    List<Goods> getAllGoods();

    Set<Goods> getGoodsInCart();

    Set<Goods> getGoodsInOrder();

    Long addGoodsByBookId(AddGoodsForm addGoodsForm);

    void removeById(IdForm idForm);
}
