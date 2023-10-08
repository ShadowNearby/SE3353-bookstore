package com.microservice.controller

import com.microservice.service.BookService
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/microservice")
class BookController(
    private val bookService: BookService,
) {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @RequestMapping(value = ["/book"], method = [RequestMethod.GET])
    fun getAuthorByName(@RequestParam("name") name: String, response: HttpServletResponse): String? {
        return bookService.getAuthorByName(name)
    }
}