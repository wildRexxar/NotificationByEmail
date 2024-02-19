package com.example.NotificationByEmail.handler.exception;

import com.example.NotificationByEmail.handler.HttpResponses;

public class GroupNotFoundException extends Exception{
    public GroupNotFoundException() {
        super(HttpResponses.GROUP_NOT_FOUND_MESSAGE.getText());
    }
}