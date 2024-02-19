package com.example.NotificationByEmail.handler.exception;

import com.example.NotificationByEmail.handler.HttpResponses;

public class TemplateNotFoundException extends Exception{
    public TemplateNotFoundException() {
        super(HttpResponses.TEMPLATE_NOT_FOUND_MESSAGE.getText());
    }
}