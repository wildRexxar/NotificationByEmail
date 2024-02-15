package com.example.NotificationByEmail.service.interfaces;

import com.example.NotificationByEmail.dto.MailCreateDto;
import org.springframework.http.ResponseEntity;

public interface AnsweringService {
    ResponseEntity<?> preparationForSend(MailCreateDto mailCreateDto);
}