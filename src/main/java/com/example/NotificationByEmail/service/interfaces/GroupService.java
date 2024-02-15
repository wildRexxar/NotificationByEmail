package com.example.NotificationByEmail.service.interfaces;

import com.example.NotificationByEmail.entity.Group;

import java.util.List;

public interface GroupService {
    int checkExistGroup(int groupUser);

    List<Group> findAll();
}