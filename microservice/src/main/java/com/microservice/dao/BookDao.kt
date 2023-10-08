package com.microservice.dao

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface BookDao {
    @Select("select author from book where name=#{name} limit 1;")
    fun getAuthorByName(@Param("name") name: String): String?
}