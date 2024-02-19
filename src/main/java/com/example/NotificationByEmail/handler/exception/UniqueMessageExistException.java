package com.example.NotificationByEmail.handler.exception;

import com.example.NotificationByEmail.handler.HttpResponses;

public class UniqueMessageExistException extends Exception{
    public UniqueMessageExistException() {
        super(HttpResponses.ALREADY_EXISTS.getText());
    }
}