package com.example.NotificationByEmail.repository;

import com.example.NotificationByEmail.entity.ErrorMail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ErrorMailRepository {
    @Insert("insert into errors (message, dateTime, status, idMail) " +
            "values (#{message}, #{date}, #{status}, #{idMail})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int save(ErrorMail errorMail);

    @Select("select * from errors")
    List<ErrorMail> findAll();
}