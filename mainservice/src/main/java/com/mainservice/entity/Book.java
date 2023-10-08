package com.mainservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 128, nullable = false)
    private String name;
    @Column(name = "image", length = 16384)
    private String image;
    @Column(name = "description", length = 2048)
    private String description;
    @Column(name = "author", length = 64, nullable = false)
    private String author;
    @Column(name = "ISBN", length = 32, nullable = false, unique = true)
    private String ISBN;
    @Column(name = "price", length = 8, nullable = false)
    private Double price;
    @Column(name = "inventory", length = 8, nullable = false)
    private Integer inventory;
    @Column(name = "deleted")
    private Boolean deleted;


    public Book(String name, String image, String description, String author, String ISBN, Double price, Integer inventory) {
        this.name = name;
        this.image = image;
        this.author = author;
        this.description = description;
        this.ISBN = ISBN;
        this.price = price;
        this.inventory = inventory;
        this.deleted = false;
    }

    public Book() {
    }

    public Book(Long id, String name, String image, String description, String author, String ISBN, Double price, Integer inventory) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.author = author;
        this.ISBN = ISBN;
        this.price = price;
        this.inventory = inventory;
        this.deleted = false;
    }
}
