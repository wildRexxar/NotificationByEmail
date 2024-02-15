package com.example.NotificationByEmail.service.interfaces;

import com.example.NotificationByEmail.entity.Template;

import java.util.List;

public interface TemplateService {
    int checkExistTemplate(int templateId);
    String findById(int templateId);
    List<Template> findAll();
}