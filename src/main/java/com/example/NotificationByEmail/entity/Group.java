package com.example.NotificationByEmail.entity;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Group {
    private int id;
    private String name;
    private LocalDate dateOfCreation;
}