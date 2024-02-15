package com.example.NotificationByEmail.service;

import com.example.NotificationByEmail.entity.Group;
import com.example.NotificationByEmail.repository.GroupRepository;
import com.example.NotificationByEmail.service.interfaces.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    @Override
    public int checkExistGroup(int groupId) {
        return findAll()
                .stream()
                .filter(group -> groupId == group.getId())
                .toList()
                .size();
    }

    @Cacheable("groups-cache")
    @Override
    public List<Group> findAll() {
        return groupRepository.findAll();
    }
}