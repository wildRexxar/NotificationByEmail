package com.example.NotificationByEmail.integration.service;

import com.example.NotificationByEmail.dto.ErrorMailCreateDto;
import com.example.NotificationByEmail.integration.TestFather;
import com.example.NotificationByEmail.service.interfaces.ErrorMailService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class ErrorMailServiceTest extends TestFather {

    private final static int SIZE = 3;
    private final static int SIZE_BY_ID = 3;
    private final static String MESSAGE = "message_test";
    private final static String STATUS = "status_test";
    private final static int MAIL_ID = 13;
    private final ErrorMailService errorMailService;

    @Test
    void findAll(){
        assertThat(errorMailService.findAll()).hasSize(SIZE);
    }

    @Test
    void save(){
        ErrorMailCreateDto errorMailCreateDto =
                new ErrorMailCreateDto(MESSAGE, STATUS, MAIL_ID);
        errorMailService.save(errorMailCreateDto);
        assertThat(errorMailService.findAll()).hasSize(SIZE + 1);
    }

    @Test
    void getByIdMail(){
        assertThat(errorMailService.getByIdMail(MAIL_ID)).hasSize(SIZE_BY_ID);
    }
}