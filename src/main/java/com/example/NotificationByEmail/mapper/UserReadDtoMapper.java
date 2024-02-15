package com.example.NotificationByEmail.mapper;

import com.example.NotificationByEmail.dto.UserReadDto;
import com.example.NotificationByEmail.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserReadDtoMapper implements Mapper<User, UserReadDto> {
    @Override
    public UserReadDto map(User user) {
        UserReadDto userReadDto = new UserReadDto();
        userReadDto.setFullname(user.getFullname());
        userReadDto.setEmail(user.getEmail());
        userReadDto.setGroupId(user.getGroupId());
        return userReadDto;
    }
}
