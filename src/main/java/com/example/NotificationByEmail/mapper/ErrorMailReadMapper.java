package com.example.NotificationByEmail.mapper;

import com.example.NotificationByEmail.dto.ErrorMailReadDto;
import com.example.NotificationByEmail.entity.ErrorMail;
import org.springframework.stereotype.Component;

@Component
public class ErrorMailReadMapper implements Mapper<ErrorMail, ErrorMailReadDto> {
    @Override
    public ErrorMailReadDto map(ErrorMail errorMail) {
        ErrorMailReadDto errorReadDto = new ErrorMailReadDto();
        errorReadDto.setMessage(errorMail.getMessage());
        errorReadDto.setStatus(errorMail.getStatus());
        return errorReadDto;
    }
}