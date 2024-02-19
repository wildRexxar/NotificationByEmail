package com.example.NotificationByEmail.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMailCreateDto {
    private String message;
    private long idMail;
}