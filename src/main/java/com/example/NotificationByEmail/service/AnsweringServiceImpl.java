package com.example.NotificationByEmail.service;

import com.example.NotificationByEmail.configuration.RabbitConfiguration;
import com.example.NotificationByEmail.dto.ErrorMailCreateDto;
import com.example.NotificationByEmail.dto.MailCreateDto;
import com.example.NotificationByEmail.entity.Letter;
import com.example.NotificationByEmail.entity.Response;
import com.example.NotificationByEmail.handler.HttpResponses;
import com.example.NotificationByEmail.service.interfaces.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class AnsweringServiceImpl implements AnsweringService {

    private final RabbitTemplate rabbitTemplate;
    private final TemplateService templateService;
    private final CoreService coreService;
    private final GroupService groupService;
    private final MailService mailService;
    private final ErrorMailService errorMailService;

    @Override
    public ResponseEntity<?> preparationForSend(MailCreateDto mailCreateDto) {
        boolean status = isExistTemplateOrGroup(mailCreateDto);
        if (!status) {
            return new ResponseEntity<>(
                    new Response(
                            HttpResponses.SOMETHING_NOT_FOUND.getCode(),
                            HttpResponses.SOMETHING_NOT_FOUND.getText()
                    ),
                    HttpStatus.BAD_REQUEST);
        }
        Map<String, String> mails = coreService.formingMails(mailCreateDto);
        queuing(mails);
        return new ResponseEntity<>(
                new Response(
                        HttpResponses.SUCCESS.getCode(),
                        HttpResponses.SUCCESS.getText()),
                HttpStatus.CREATED);
    }

    private void queuing(Map<String, String> mails) {
        Set<String> emails = mails.keySet();
        for (String email : emails) {
            String message = mails.get(email);
            Letter letter = new Letter(email, message);
            rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE, RabbitConfiguration.QUEUE, letter);
        }
    }

    private boolean isExistTemplateOrGroup(MailCreateDto mailCreateDto) {
        int errorFlag = 0;
        if (templateService.checkExistTemplate(mailCreateDto.getTemplateId()) == 0) {
            long mailId = mailService.getMailIdByUniqueMessage(mailCreateDto.getUniqueMessage());
            saveError(new ErrorMailCreateDto(HttpResponses.TEMPLATE_NOT_FOUND_MESSAGE.getText(),
                    HttpResponses.TEMPLATE_NOT_FOUND_MESSAGE.getCode(), mailId));
            errorFlag = 1;
        }
        if (groupService.checkExistGroup(mailCreateDto.getGroupUser()) == 0) {
            long mailId = mailService.getMailIdByUniqueMessage(mailCreateDto.getUniqueMessage());
            saveError(new ErrorMailCreateDto(HttpResponses.GROUP_NOT_FOUND_MESSAGE.getText(),
                    HttpResponses.GROUP_NOT_FOUND_MESSAGE.getCode(), mailId));
            errorFlag = 1;
        }
        return errorFlag == 0;
    }

    private void saveError(ErrorMailCreateDto errorMailCreateDto) {
        errorMailService.save(errorMailCreateDto);
    }
}