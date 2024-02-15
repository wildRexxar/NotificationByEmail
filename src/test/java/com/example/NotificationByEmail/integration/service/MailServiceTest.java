package com.example.NotificationByEmail.integration.service;

import com.example.NotificationByEmail.dto.MailCreateDto;
import com.example.NotificationByEmail.dto.MailReadDto;
import com.example.NotificationByEmail.integration.TestFather;
import com.example.NotificationByEmail.service.interfaces.ErrorMailService;
import com.example.NotificationByEmail.service.interfaces.MailService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
public class MailServiceTest extends TestFather {

    private static final int ID = 1;
    private final long MAIL_ID = 13L;
    private static final String NEW_UNIQ_MESSAGE = "0000010";
    private static final String UNIQ_MESSAGE_EXIST = "0000003";
    private static final String UNIQ_MESSAGE_NOT_EXIST = "notExistUniqueMessage";

    private final MailService mailService;
    private final ErrorMailService errorMailService;

    @Test
    void getAllMails() {
        List<MailReadDto> mails = mailService.getAllMails();
        assertThat(mails).hasSize(6);
    }

    @Test
    void saveWithoutError() {
        int mailsCountBefore = mailService.getAllMails().size();
        int errorsCountBefore = errorMailService.findAll().size();
        MailCreateDto mailCreateDto = new MailCreateDto();
        mailCreateDto.setUniqueMessage(NEW_UNIQ_MESSAGE);
        mailCreateDto.setGroupUser(ID);
        mailCreateDto.setTemplateId(ID);
        mailCreateDto.setFile("0010111100100100");
        mailCreateDto.setTypeFile("PNG");
        mailCreateDto.setData(new HashMap<String, String>());
        mailService.save(mailCreateDto);
        int mailsCountAfter = mailService.getAllMails().size();
        int errorsCountAfter = errorMailService.findAll().size();
        assertEquals(mailsCountBefore + 1, mailsCountAfter);
        assertEquals(errorsCountBefore, errorsCountAfter);
    }

    @Test
    void checkExistMailByUniqMessage_MailNotFound() {
        int status = mailService.checkExistMail(UNIQ_MESSAGE_EXIST);
        assertEquals(status, 1);
    }

    @Test
    void checkNotExistMailByUniqMessage_MailExist() {
        int status = mailService.checkExistMail(UNIQ_MESSAGE_NOT_EXIST);
        assertEquals(status, 0);
    }

    @Test
    void getMailByUniqMessage_MailNotFound() {
        Optional<MailReadDto> mailReadDto = mailService.getByUniqMessage(UNIQ_MESSAGE_NOT_EXIST);
        assertFalse(mailReadDto.isPresent());
    }

    @Test
    void getMailByUniqMessage_MailExist() {
        Optional<MailReadDto> mailReadDto = mailService.getByUniqMessage(UNIQ_MESSAGE_EXIST);
        assertTrue(mailReadDto.isPresent());
        String uniqueMessage = mailReadDto.get().getUniqueMessage();
        assertEquals(uniqueMessage, UNIQ_MESSAGE_EXIST);
    }

    @Test
    void getMailIdByUniqueMessage() {
        long mailId = mailService.getMailIdByUniqueMessage(UNIQ_MESSAGE_EXIST);
        assertEquals(mailId, MAIL_ID);
    }
}