package com.example.NotificationByEmail.repository;

import com.example.NotificationByEmail.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface UserRepository {
    @Select("select * from users")
    List<User> findAll();
}