package com.example.bookstore.util.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
public class AddOrderForm {
    private Set<Long> orderItemIds;
    private Long userId;
}
