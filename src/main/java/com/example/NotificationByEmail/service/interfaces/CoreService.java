package com.example.NotificationByEmail.service.interfaces;

import com.example.NotificationByEmail.dto.MailCreateDto;
import java.util.Map;

public interface CoreService {
    Map<String, String> formingMails(MailCreateDto mailCreateDto);
}