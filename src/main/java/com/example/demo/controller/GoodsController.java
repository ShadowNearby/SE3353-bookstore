package com.example.demo.controller;

import com.example.demo.entity.Goods;
import com.example.demo.service.GoodsService;
import com.example.demo.util.request.AddGoodsForm;
import com.example.demo.util.request.IdForm;
import org.jetbrains.annotations.NotNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@Transactional
public class GoodsController {
    private final GoodsService goodsService;

    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @RequestMapping(value = "/api/goods/cart/user/{userId}", method = RequestMethod.GET)
    public Set<Goods> getGoodsInCart(@PathVariable Long userId) {
        return goodsService.getGoodsInCart(userId);
    }

    @RequestMapping(value = "/api/goods/order/user/{userId}", method = RequestMethod.GET)
    public Set<Goods> getGoodsInOrder(@PathVariable Long userId) {
        return goodsService.getGoodsInOrder(userId);
    }

    @RequestMapping(value = "/api/goods/cart/add", method = RequestMethod.POST)
    public Long addGoodsToCart(@RequestBody @NotNull AddGoodsForm addGoodsForm) {
        return goodsService.addGoodsByBookIdUserId(addGoodsForm);
    }

    @RequestMapping(value = "/api/goods/cart/remove", method = RequestMethod.POST)
    public void addGoodsToCart(@RequestBody @NotNull IdForm idForm) {
        goodsService.removeById(idForm);
    }

    @RequestMapping(value = "/api/goods/order/add", method = RequestMethod.POST)
    public void addGoodsToOrder(@RequestBody @NotNull AddGoodsForm addGoodsForm) {
        goodsService.addGoodsByBookIdUserId(addGoodsForm);
    }

}
