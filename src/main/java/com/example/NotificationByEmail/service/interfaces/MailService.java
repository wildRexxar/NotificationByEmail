package com.example.NotificationByEmail.service.interfaces;

import com.example.NotificationByEmail.dto.MailCreateDto;
import com.example.NotificationByEmail.dto.MailReadDto;
import com.example.NotificationByEmail.entity.Mail;

import java.util.List;
import java.util.Optional;

public interface MailService {
     int save(MailCreateDto mailCreateDto);
     List<MailReadDto> getAllMails();
     Optional<MailReadDto> getByUniqMessage(String uniqMessage);
     int checkExistMail(String uniqueMessage);
     long getMailIdByUniqueMessage(String uniqueMessage);
     List<Mail> findAll();
}