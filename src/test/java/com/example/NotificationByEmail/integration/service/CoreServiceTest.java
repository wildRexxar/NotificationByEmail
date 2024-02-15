package com.example.NotificationByEmail.integration.service;

import com.example.NotificationByEmail.dto.MailCreateDto;
import com.example.NotificationByEmail.entity.Mail;
import com.example.NotificationByEmail.integration.TestFather;
import com.example.NotificationByEmail.service.interfaces.CoreService;
import com.example.NotificationByEmail.service.interfaces.MailService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
public class CoreServiceTest extends TestFather {

    private final static int SIZE = 2;

    private final static String MESSAGE = "Attention testing, testing";
    private final static String EMAIL_1 = "max@mail.com";
    private final static String EMAIL_2 = "sam@mail.com";

    private final CoreService coreService;
    private final MailService mailService;

    @Test
    void formingMails() {
        Mail mail = getFirstMail();
        Map<String, String> map =coreService.formingMails(getMailCreateDto(mail));
        assertThat(map).hasSize(SIZE);
        assertEquals(map.get(EMAIL_1), MESSAGE);
        assertEquals(map.get(EMAIL_2), MESSAGE);
    }

    private Mail getFirstMail(){
        return mailService.findAll().stream().findFirst().get();
    }

    private MailCreateDto getMailCreateDto(Mail mail) {
        MailCreateDto mailCreateDto = new MailCreateDto();
        mailCreateDto.setUniqueMessage(mail.getUniqueMessage() + "-");
        mailCreateDto.setGroupUser(mail.getGroupUser());
        mailCreateDto.setTemplateId(mail.getTemplateId());
        mailCreateDto.setFile(mail.getFile());
        mailCreateDto.setTypeFile(mail.getTypeFile());
        mailCreateDto.setData(getData());
        return mailCreateDto;
    }

    private Map<String, String> getData() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "testing");
        map.put("message", "testing");
        return map;
    }
}