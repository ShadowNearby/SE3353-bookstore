package com.example.bookstore.controller;

import com.example.bookstore.entity.Goods;
import com.example.bookstore.service.GoodsService;
import com.example.bookstore.util.request.AddGoodsForm;
import com.example.bookstore.util.request.IdForm;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@Transactional
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

//    public GoodsController(GoodsService goodsService) {
//        this.goodsService = goodsService;
//    }

    @RequestMapping(value = "/api/goods/cart", method = RequestMethod.GET)
    public Set<Goods> getGoodsInCart() {
        return goodsService.getGoodsInCart();
    }

    @RequestMapping(value = "/api/goods/order", method = RequestMethod.GET)
    public Set<Goods> getGoodsInOrder() {
        return goodsService.getGoodsInOrder();
    }

    @RequestMapping(value = "/api/goods/cart/add", method = RequestMethod.POST)
    public Long addGoodsToCart(@RequestBody @NotNull AddGoodsForm addGoodsForm) {
        return goodsService.addGoodsByBookId(addGoodsForm);
    }

    @RequestMapping(value = "/api/goods/cart/remove", method = RequestMethod.POST)
    public void addGoodsToCart(@RequestBody @NotNull IdForm idForm) {
        goodsService.removeById(idForm);
    }

    @RequestMapping(value = "/api/goods/order/add", method = RequestMethod.POST)
    public void addGoodsToOrder(@RequestBody @NotNull AddGoodsForm addGoodsForm) {
        goodsService.addGoodsByBookId(addGoodsForm);
    }

}
