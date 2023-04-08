package com.example.bookstore.util.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserPutForm {
    private Long id;
    private String username;
    private String avatar;
    private String email;
    private String role;
    private Date registerTime;
    private Boolean banned;
}
