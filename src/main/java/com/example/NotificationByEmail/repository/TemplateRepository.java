package com.example.NotificationByEmail.repository;

import com.example.NotificationByEmail.entity.Template;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TemplateRepository {
    @Select("select * from templates")
    List<Template> findAll();
}