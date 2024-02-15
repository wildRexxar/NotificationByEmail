package com.example.NotificationByEmail.entity;

import lombok.Data;
import java.util.List;

@Data
public class Mail {
    private long id;
    private String uniqueMessage;
    private int groupUser;
    private int templateId;
    private String file;
    private String typeFile;
    private String data;
    private List<ErrorMail> errorMails;
}