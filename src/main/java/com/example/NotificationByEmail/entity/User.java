package com.example.NotificationByEmail.entity;

import lombok.Data;
import java.time.LocalDate;

@Data
public class User {
    private long id;
    private String fullname;
    private String email;
    private int groupId;
    private LocalDate dateOfCreation;
}