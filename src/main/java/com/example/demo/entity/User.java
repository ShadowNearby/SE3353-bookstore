package com.example.demo.entity;

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
    @Column(name = "account", length = 128, nullable = false, unique = true)
    private String account;
    @Column(name = "password", length = 128, nullable = false)
    private String password;
    @Column(name = "email", length = 128, nullable = false, unique = true)
    private String email;
    @Column(name = "avatar", length = 10240)
    private String avatar;
    @Column(name = "role", nullable = false)
    private Boolean role;
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

    public User(String account, String password, String email, Boolean role, Date registerTime) {
        this.account = account;
        this.password = password;
        this.email = email;
        this.role = role;
        this.avatar = "";
        this.registerTime = registerTime;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatar() {
        return avatar;
    }

    public Boolean getRole() {
        return role;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public List<Order> getOrderList() {
        return orderList;
    }
}