package com.example.NotificationByEmail.controller;

import com.example.NotificationByEmail.dto.MailCreateDto;
import com.example.NotificationByEmail.service.interfaces.MailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/mails")
@RequiredArgsConstructor
@Slf4j
public class MailController {

    private final MailService mailService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody MailCreateDto mailCreateDto) {
        mailService.save(mailCreateDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(mailService.getAllMails(), HttpStatus.OK);
    }

    @GetMapping("/{uniqMessage}")
    public ResponseEntity<?> getByUniqMess(@PathVariable String uniqMessage) {
        return new ResponseEntity<>(mailService.getByUniqMessage(uniqMessage), HttpStatus.OK);
    }
}