package com.example.NotificationByEmail.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SmtpMailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.port}")
    private String port;

    @Value("${spring.mail.protocol}")
    private String protocol;

    public void sendEmail(String email, String message) {
        if (propertyCheck() == 1) {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(email);
            mailMessage.setSubject(message);
            mailMessage.setText(message);
            javaMailSender.send(mailMessage);
        }
    }

    private int propertyCheck() {
        if (!host.isEmpty() && !username.isEmpty() && !password.isEmpty()
                && !port.isEmpty() && !protocol.isEmpty()) {
            return 1;
        }
        return 0;
    }
}