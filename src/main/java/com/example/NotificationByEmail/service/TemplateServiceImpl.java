package com.example.NotificationByEmail.service;

import com.example.NotificationByEmail.entity.Template;
import com.example.NotificationByEmail.repository.TemplateRepository;
import com.example.NotificationByEmail.service.interfaces.TemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TemplateServiceImpl implements TemplateService {

    private final TemplateRepository templateRepository;

    @Override
    public int checkExistTemplate(int templateId) {
        return findAll()
                .stream()
                .filter(template -> templateId == template.getId())
                .toList()
                .size();
    }

    @Override
    public String findById(int templateId) {
        return findAll()
                .stream()
                .filter(template -> templateId == template.getId())
                .findFirst()
                .map(Template::getTemplate)
                .get();
    }

    @Cacheable("templates-cache")
    @Override
    public List<Template> findAll() {
        return templateRepository.findAll();
    }
}