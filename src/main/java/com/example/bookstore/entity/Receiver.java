package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "receiver")
public class Receiver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 128, nullable = false)
    private String name;
    @Column(name = "address", length = 1024, nullable = false)
    private String address;
    @Column(name = "phone", length = 64, nullable = false, unique = true)
    private String phone;
//    @JsonIgnoreProperties(value = {"receiver"})
//    @OneToOne(mappedBy = "receiver", cascade = CascadeType.REMOVE)
//    private Order order;
}