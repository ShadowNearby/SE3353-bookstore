package com.example.demo.controller;

import com.example.demo.entity.Goods;
import com.example.demo.service.GoodsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GoodsController {
    private final GoodsService goodsService;

    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @RequestMapping(value = "/api/goods", method = RequestMethod.GET)
    public List<Goods> getGoods() {
        return goodsService.getAllGoods();
    }
}
