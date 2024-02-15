package com.example.NotificationByEmail.service;

import com.example.NotificationByEmail.dto.MailCreateDto;
import com.example.NotificationByEmail.dto.UserReadDto;
import com.example.NotificationByEmail.service.interfaces.CoreService;
import com.example.NotificationByEmail.service.interfaces.TemplateService;
import com.example.NotificationByEmail.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class CoreServiceImpl implements CoreService {

    private final TemplateService templateService;
    private final UserService userService;

    @Override
    public Map<String, String> formingMails(MailCreateDto mailCreateDto) {
        String text = getMessage(mailCreateDto.getData(), mailCreateDto.getTemplateId());
        List<UserReadDto> users = getUsersByGroup(mailCreateDto.getGroupUser());
        Map<String, String> mails = new HashMap<>();
        for (UserReadDto user : users) {
            mails.put(user.getEmail(), text);
        }
        return mails;
    }

    private String getMessage(Map<String, String> data, Integer templateId) {
        return formingMessage(data, templateService.findById(templateId));
    }

    private List<UserReadDto> getUsersByGroup(Integer groupId) {
        return userService.findByIdGroup(groupId);
    }

    private String formingMessage(Map<String, String> data, String template) {
        Set<String> keySet = data.keySet();
        StringBuilder text = new StringBuilder(template);
        for (String element : keySet) {
            int start = text.indexOf("%" + element);
            if (start < 0) {
                continue;
            }
            int finish = start + ("%" + element).length();
            text.replace(start, finish, data.get(element));
        }
        return text.toString();
    }
}