package com.example.NotificationByEmail.entity;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Response {
    private String code;
    private String message;
    private LocalDateTime localDateTime;

    public Response(String code, String message){
        this.code = code;
        this.message = message;
        this.localDateTime = LocalDateTime.now();
    }
}