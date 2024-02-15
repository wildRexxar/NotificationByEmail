package com.example.NotificationByEmail.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Letter {
    private String email;
    private String message;
}