package com.example.NotificationByEmail.handler;

import lombok.Getter;

@Getter
public enum HttpResponses {

    ALREADY_EXISTS("Message is already exist"),
    SOMETHING_WENT_WRONG("Something went wrong"),
    SUCCESS("Success"),
    MESSAGE_NOT_FOUND("Message not found"),
    SOMETHING_NOT_FOUND("Group or template is incorrect"),
    TEMPLATE_NOT_FOUND_MESSAGE("Template not found"),
    GROUP_NOT_FOUND_MESSAGE("Group not found");

    private String text;

    HttpResponses(String text) {
        this.text = text;
    }
}