package com.example.NotificationByEmail.service;

import com.example.NotificationByEmail.configuration.RabbitConfiguration;
import com.example.NotificationByEmail.dto.MailCreateDto;
import com.example.NotificationByEmail.entity.Letter;
import com.example.NotificationByEmail.service.interfaces.AnsweringService;
import com.example.NotificationByEmail.service.interfaces.CoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class AnsweringServiceImpl implements AnsweringService {

    private final RabbitTemplate rabbitTemplate;
    private final CoreService coreService;

    @Override
    public void preparationForSend(MailCreateDto mailCreateDto) {
        Map<String, String> mails = coreService.formingMails(mailCreateDto);
        queuing(mails);
    }

    private void queuing(Map<String, String> mails) {
        Set<String> emails = mails.keySet();
        for (String email : emails) {
            String message = mails.get(email);
            Letter letter = new Letter(email, message);
            rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE, RabbitConfiguration.QUEUE, letter);
        }
    }
}