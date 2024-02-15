package com.example.NotificationByEmail.mapper;

import com.example.NotificationByEmail.dto.ErrorMailReadDto;
import com.example.NotificationByEmail.dto.MailReadDto;
import com.example.NotificationByEmail.entity.Mail;
import com.example.NotificationByEmail.service.interfaces.ErrorMailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MailReadMapper implements Mapper<Mail, MailReadDto> {

    private final ErrorMailService errorMailService;
    private final ErrorMailReadMapper errorMailReadMapper;

    @Override
    public MailReadDto map(Mail mail) {
        MailReadDto mailReadDto = new MailReadDto();
        mailReadDto.setUniqueMessage(mail.getUniqueMessage());
        mailReadDto.setGroupUser(mail.getGroupUser());
        mailReadDto.setTemplateId(mail.getTemplateId());
        mailReadDto.setFile(mail.getFile());
        mailReadDto.setTypeFile(mail.getTypeFile());
        mailReadDto.setData(convertStringToMap(mail.getData()));
        mailReadDto.setErrors(getListErrors(mail.getId()));

        return mailReadDto;
    }

    private List<ErrorMailReadDto> getListErrors(long id) {
        List<ErrorMailReadDto> errors = errorMailService.findAll()
                .stream()
                .filter(error -> error.getIdMail() == id)
                .map(errorMailReadMapper::map)
                .toList();
        return errors;
    }

    @SneakyThrows
    private HashMap convertStringToMap(String data) {
        return new ObjectMapper().readValue(data, HashMap.class);
    }
}