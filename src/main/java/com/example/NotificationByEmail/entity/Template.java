package com.example.NotificationByEmail.entity;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Template {
    private int id;
    private String template;
    private LocalDate dateOfCreation;
}