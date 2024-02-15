package com.example.NotificationByEmail.integration.service;

import com.example.NotificationByEmail.integration.TestFather;
import com.example.NotificationByEmail.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class UserServiceTest extends TestFather {

    private final static int SIZE = 6;
    private final static int USER_SIZE = 2;
    private final static int GROUP_ID = 1;
    private final static int GROUP_ID_NOT_EXIST = 10;
    private final static int EMPTY_RESULT = 0;
    private final UserService userService;

    @Test
    void findAll(){
        assertThat(userService.findAll()).hasSize(SIZE);
    }

    @Test
    void findByIdGroup(){
        assertThat(userService.findByIdGroup(GROUP_ID)).hasSize(USER_SIZE);
        assertThat(userService.findByIdGroup(GROUP_ID_NOT_EXIST)).hasSize(EMPTY_RESULT);
    }
}