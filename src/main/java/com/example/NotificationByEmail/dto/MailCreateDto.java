package com.example.NotificationByEmail.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Map;

@Data
public class MailCreateDto {

    private final static String UNIQUE_MESSAGE = "Unique message can not be empty or blank";
    private final static String GROUP_ID = "Group id can not be negative";
    private final static String TEMPLATE_ID = "Template id can not be negative";
    private final static String FILE = "Invalid characters in file";
    private final static String FILE_TYPE = "File type can be only PDF or PNG";
    private final static String DATA = "Max size for data can be equals 3";

    @NotBlank(message = UNIQUE_MESSAGE)
    private String uniqueMessage;

    @PositiveOrZero(message = GROUP_ID)
    private int groupUser;

    @PositiveOrZero(message = TEMPLATE_ID)
    private int templateId;

    @Pattern(regexp = "^\\d*$", message = FILE)
    private String file;

    @Pattern(regexp = "^(PDF)|(PNG)$", message = FILE_TYPE)
    private String typeFile;

    @Size(max = 3, message = DATA)
    private Map<String, String> data;
}