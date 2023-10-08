package com.microservice.service.impl

import com.microservice.dao.BookDao
import com.microservice.service.BookService
import org.springframework.stereotype.Service

@Service
class BookServiceImpl(private val bookDao: BookDao) : BookService {
    override fun getAuthorByName(name: String): String? {
        return bookDao.getAuthorByName(name = name)
    }
}