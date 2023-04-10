package com.example.bookstore.util.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StatisticForm {
    private Date beginDate;
    private Date endDate;
}
