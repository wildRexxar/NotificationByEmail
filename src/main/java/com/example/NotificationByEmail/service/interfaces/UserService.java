package com.example.NotificationByEmail.service.interfaces;

import com.example.NotificationByEmail.dto.UserReadDto;
import com.example.NotificationByEmail.entity.User;

import java.util.List;

public interface UserService {
    List<UserReadDto> findByIdGroup(int groupId);

    List<User> findAll();
}