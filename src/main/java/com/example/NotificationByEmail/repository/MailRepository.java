package com.example.NotificationByEmail.repository;

import com.example.NotificationByEmail.entity.Mail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MailRepository {
    @Select("select * from mails")
    List<Mail> findAll();

    @Insert("insert into mails (uniqueMessage, groupUser, templateId, file, typeFile, data) " +
            "values (#{uniqueMessage}, #{groupUser}, #{templateId}, #{file}, #{typeFile}, #{data})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int save(Mail message);
}