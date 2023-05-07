package com.example.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.List;

@Getter
@Setter
@Entity
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
    @JsonIgnoreProperties(value = {"books"})
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book_tag",
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"),
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id")
    )
    private List<Tag> tags;

    public Book(String name, String image, String description, String author, String ISBN, Double price, Integer inventory, List<Tag> tags) {
        this.name = name;
        this.image = image;
        this.author = author;
        this.description = description;
        this.ISBN = ISBN;
        this.price = price;
        this.inventory = inventory;
        this.tags = tags;
    }

    public Book() {
    }

    public Book(Long id, String name, String image, String description, String author, String ISBN, Double price, Integer inventory, List<Tag> tags) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.author = author;
        this.ISBN = ISBN;
        this.price = price;
        this.inventory = inventory;
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return new EqualsBuilder().append(id, book.id).append(name, book.name).append(image, book.image).append(description, book.description).append(author, book.author).append(ISBN, book.ISBN).append(price, book.price).append(inventory, book.inventory).append(tags, book.tags).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(name).append(image).append(description).append(author).append(ISBN).append(price).append(inventory).append(tags).toHashCode();
    }
}
