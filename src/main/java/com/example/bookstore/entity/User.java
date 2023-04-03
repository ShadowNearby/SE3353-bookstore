package com.example.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "user")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", length = 128, nullable = false, unique = true)
    private String username;
    @Column(name = "password", length = 128, nullable = false)
    private String password;
    @Column(name = "email", length = 128, nullable = false, unique = true)
    private String email;
    @Column(name = "avatar", length = 10240)
    private String avatar;
    @Column(name = "role", nullable = false)
    private String role;
    @Column(name = "registertime", length = 128, nullable = false)
    private Date registerTime;
    @JsonIgnoreProperties(value = {"user", "goodsList"})
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orderList;
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Goods> goodsList;
    @Column(name = "banned", nullable = false)
    private Boolean banned = false;

    public User(String username, String password, String email, String role, Date registerTime) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.avatar = "";
        this.registerTime = registerTime;
    }

    public User() {

    }

}