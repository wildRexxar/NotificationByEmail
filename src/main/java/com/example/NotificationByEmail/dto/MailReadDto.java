package com.example.NotificationByEmail.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class MailReadDto {
    private String uniqueMessage;
    private int groupUser;
    private int templateId;
    private String file;
    private String typeFile;
    private Map<String, String> data;
    private List<ErrorMailReadDto> errors;
}