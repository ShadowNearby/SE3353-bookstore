package com.microservice.entity

import jakarta.persistence.Column
import jakarta.persistence.Table
import lombok.Getter
import lombok.Setter

@Getter
@Setter
@Table(name = "book")
class Book {
    @Column(name = "name", length = 128, nullable = false)
    private var name: String? = null

    @Column(name = "author", length = 64, nullable = false)
    private var author: String? = null
}