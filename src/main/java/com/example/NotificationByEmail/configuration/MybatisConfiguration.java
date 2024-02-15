package com.example.NotificationByEmail.configuration;

import com.example.NotificationByEmail.entity.*;
import com.example.NotificationByEmail.entity.ErrorMail;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MappedTypes({Mail.class, ErrorMail.class, Template.class, User.class, Group.class})
@MapperScan("com.example.NotificationByEmail.repository")
public class MybatisConfiguration {
}