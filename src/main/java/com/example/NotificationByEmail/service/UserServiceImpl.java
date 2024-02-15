package com.example.NotificationByEmail.service;

import com.example.NotificationByEmail.dto.UserReadDto;
import com.example.NotificationByEmail.entity.User;
import com.example.NotificationByEmail.mapper.UserReadDtoMapper;
import com.example.NotificationByEmail.repository.UserRepository;
import com.example.NotificationByEmail.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserReadDtoMapper userReadDtoMapper;

    @Override
    public List<UserReadDto> findByIdGroup(int groupId) {
        return findAll()
                .stream()
                .map(userReadDtoMapper::map)
                .filter(user -> groupId == user.getGroupId())
                .collect(Collectors.toList());
    }


    @Cacheable("users-cache")
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}