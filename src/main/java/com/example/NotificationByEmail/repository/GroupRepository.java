package com.example.NotificationByEmail.repository;

import com.example.NotificationByEmail.entity.Group;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GroupRepository {
    @Select("select * from groups")
    List<Group> findAll();
}