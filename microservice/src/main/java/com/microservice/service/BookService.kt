package com.microservice.service

interface BookService {
    fun getAuthorByName(name: String): String?
}