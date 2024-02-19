package com.example.NotificationByEmail.mapper;

import com.example.NotificationByEmail.dto.ErrorMailCreateDto;
import com.example.NotificationByEmail.entity.ErrorMail;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ErrorMailCreateMapper implements Mapper<ErrorMailCreateDto, ErrorMail> {

    @Override
    public ErrorMail map(ErrorMailCreateDto errorCreateDto) {
        ErrorMail errorMail = new ErrorMail();
        errorMail.setMessage(errorCreateDto.getMessage());
        errorMail.setDate(LocalDateTime.now());
        errorMail.setIdMail(errorCreateDto.getIdMail());
        return errorMail;
    }
}