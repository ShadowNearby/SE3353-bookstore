package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
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
    @Column(name = "introduction", length = 1024)
    private String introduction;
    @Column(name = "role", length = 16, nullable = false)
    private Boolean role;
    @Column(name = "registertime", length = 128, nullable = false)
    private Date registerTime;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Order> orderList;

    public User(String account, String password, String email, Boolean role, Date registerTime) {
        this.account = account;
        this.password = password;
        this.email = email;
        this.role = role;
        this.introduction = "";
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

    public String getIntroduction() {
        return introduction;
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