package com.example.NotificationByEmail.listener;

import com.example.NotificationByEmail.configuration.RabbitConfiguration;
import com.example.NotificationByEmail.entity.Letter;
import com.example.NotificationByEmail.service.SmtpMailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@EnableRabbit
@Component
@RequiredArgsConstructor
public class RabbitMQListener {

    private final SmtpMailService smtpMailService;

    @RabbitListener(queues = RabbitConfiguration.QUEUE)
    public void processEmailNotification(Letter letter) {
        smtpMailService.sendEmail(letter.getEmail(), letter.getMessage());
    }
}