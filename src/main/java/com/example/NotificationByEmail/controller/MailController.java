package com.example.NotificationByEmail.controller;

import com.example.NotificationByEmail.dto.MailCreateDto;
import com.example.NotificationByEmail.dto.MailReadDto;
import com.example.NotificationByEmail.entity.Response;
import com.example.NotificationByEmail.handler.HttpResponses;
import com.example.NotificationByEmail.service.interfaces.AnsweringService;
import com.example.NotificationByEmail.service.interfaces.MailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/mails")
@RequiredArgsConstructor
@Slf4j
public class MailController {

    private final MailService mailService;
    private final AnsweringService answeringService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody MailCreateDto mailCreateDto) {
        if (mailService.checkExistMail(mailCreateDto.getUniqueMessage()) != 0) {
            return new ResponseEntity<>(
                    new Response(HttpResponses.ALREADY_EXISTS.getCode(), HttpResponses.ALREADY_EXISTS.getText()),
                    HttpStatus.BAD_REQUEST);
        }
        mailService.save(mailCreateDto);
        return answeringService.preparationForSend(mailCreateDto);
    }

        @GetMapping
        public ResponseEntity<?> getAll () {
            List<MailReadDto> mailReadDtoList = mailService.getAllMails();
            if (!mailReadDtoList.isEmpty()) {
                return new ResponseEntity<>(mailReadDtoList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }

        @GetMapping("/{uniqMessage}")
        public ResponseEntity<?> getByUniqMess (@PathVariable String uniqMessage){
            if (mailService.checkExistMail(uniqMessage) == 0) {
                return new ResponseEntity<>(HttpResponses.MESSAGE_NOT_FOUND.getText(), HttpStatus.BAD_REQUEST);
            }
            return mailService.getByUniqMessage(uniqMessage)
                    .map(messages -> new ResponseEntity<>(messages, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
        }
    }