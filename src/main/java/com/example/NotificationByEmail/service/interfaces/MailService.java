package com.example.NotificationByEmail.service.interfaces;

import com.example.NotificationByEmail.dto.MailCreateDto;
import com.example.NotificationByEmail.dto.MailReadDto;
import com.example.NotificationByEmail.entity.Mail;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface MailService {
     void save(MailCreateDto mailCreateDto);
     List<MailReadDto> getAllMails();
     MailReadDto getByUniqMessage(String uniqMessage);
     long getMailIdByUniqueMessage(String uniqueMessage);
     List<Mail> findAll();
}