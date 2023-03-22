package com.example.demo.request;

import lombok.Getter;

import java.util.Set;

@Getter
public class AddOrderForm {
    private Set<Long> goodsIds;
    private Long userId;
}
