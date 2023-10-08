package com.mainservice.util.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserStatisticsForm {
    private String username;
    private Double spend;

    public UserStatisticsForm(String username, Double spend) {
        this.username = username;
        this.spend = spend;
    }
}
