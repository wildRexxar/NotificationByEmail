package com.example.NotificationByEmail.handler;

import lombok.Getter;

@Getter
public enum HttpResponses {

    ALREADY_EXISTS("001", "Message is already exist"),
    SOMETHING_WENT_WRONG("002", "Something went wrong"),
    SUCCESS("003", "Success"),
    MESSAGE_NOT_FOUND("004", "Message not found"),
    SOMETHING_NOT_FOUND("005", "Group or template is incorrect"),
    TEMPLATE_NOT_FOUND_MESSAGE("006", "Template not found"),
    GROUP_NOT_FOUND_MESSAGE("007", "Group not found");

    private String code;
    private String text;

    HttpResponses(String code, String text) {
        this.code = code;
        this.text = text;
    }
}