package com.example.NotificationByEmail.integration.service;

import com.example.NotificationByEmail.integration.TestFather;
import com.example.NotificationByEmail.service.interfaces.TemplateService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
public class TemplateServiceTest extends TestFather {

    private static final String TEMPLATE_EXAMPLE = "Dear %name, %message";
    private static final int ID = 1;
    private static final int ID_NOT_EXIST = 10;
    private static final int SIZE = 3;

    private final TemplateService templateService;

    @Test
    void findAll() {
        assertThat(templateService.findAll()).hasSize(SIZE);
    }

    @Test
    void findById() {
        String template = templateService.findById(ID);
        assertEquals(template, TEMPLATE_EXAMPLE);
    }

    @Test
    void checkExistTemplate() {
        int template = templateService.checkExistTemplate(ID);
        assertEquals(template, 1);
    }

    @Test
    void checkExistTemplate_NotExist() {
        int template = templateService.checkExistTemplate(ID_NOT_EXIST);
        assertEquals(template, 0);
    }
}