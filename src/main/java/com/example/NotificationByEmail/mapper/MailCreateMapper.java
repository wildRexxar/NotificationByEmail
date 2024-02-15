package com.example.NotificationByEmail.mapper;

import com.example.NotificationByEmail.dto.MailCreateDto;
import com.example.NotificationByEmail.entity.Mail;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class MailCreateMapper implements Mapper<MailCreateDto, Mail> {
    @Override
    public Mail map(MailCreateDto mailCreateDto) {
        Mail mail = new Mail();
        mail.setUniqueMessage(mailCreateDto.getUniqueMessage());
        mail.setGroupUser(mailCreateDto.getGroupUser());
        mail.setTemplateId(mailCreateDto.getTemplateId());
        mail.setFile(mailCreateDto.getFile());
        mail.setTypeFile(mailCreateDto.getTypeFile());
        mail.setData(new JSONObject(mailCreateDto.getData()).toString());
        return mail;
    }
}