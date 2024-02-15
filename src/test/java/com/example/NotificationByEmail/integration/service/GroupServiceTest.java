package com.example.NotificationByEmail.integration.service;

import com.example.NotificationByEmail.integration.TestFather;
import com.example.NotificationByEmail.service.interfaces.GroupService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
public class GroupServiceTest extends TestFather {

    private final static int SIZE = 3;
    private final static int GROUP_ID_EXIST = 1;
    private final static int GROUP_ID_NOT_EXIST = 10;
    private final static int GROUP_NOT_EXIST = 0;
    private final static int GROUP_EXIST = 1;
    private final GroupService groupService;

    @Test
    void findAll(){
        assertThat(groupService.findAll()).hasSize(SIZE);
    }

    @Test
    void checkExistGroup_Exist(){
        int status = groupService.checkExistGroup(GROUP_ID_EXIST);
        assertEquals(status, GROUP_EXIST);
    }

    @Test
    void checkExistGroup_NotExist(){
        int status = groupService.checkExistGroup(GROUP_ID_NOT_EXIST);
        assertEquals(status, GROUP_NOT_EXIST);
    }
}