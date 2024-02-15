package com.example.NotificationByEmail.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorMail {
    private long id;
    private String message;
    private LocalDateTime date;
    private String status;
    private long idMail;
}