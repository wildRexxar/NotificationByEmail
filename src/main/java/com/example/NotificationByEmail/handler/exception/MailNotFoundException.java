package com.example.NotificationByEmail.handler.exception;

import com.example.NotificationByEmail.handler.HttpResponses;

public class MailNotFoundException extends Exception{
    public MailNotFoundException() {
        super(HttpResponses.MESSAGE_NOT_FOUND.getText());
    }
}