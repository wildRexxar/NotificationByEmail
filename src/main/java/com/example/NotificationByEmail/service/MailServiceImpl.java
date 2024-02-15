package com.example.NotificationByEmail.service;

import com.example.NotificationByEmail.dto.MailCreateDto;
import com.example.NotificationByEmail.dto.MailReadDto;
import com.example.NotificationByEmail.entity.Mail;
import com.example.NotificationByEmail.mapper.MailCreateMapper;
import com.example.NotificationByEmail.mapper.MailReadMapper;
import com.example.NotificationByEmail.repository.MailRepository;
import com.example.NotificationByEmail.service.interfaces.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class MailServiceImpl implements MailService {

    private final MailRepository mailRepository;
    private final MailCreateMapper mailCreateMapper;
    private final MailReadMapper mailReadMapper;
    private final CacheManager cacheManager;

    @Override
    public int save(MailCreateDto mailCreateDto) {
        int status = Optional
                .of(mailCreateDto)
                .map(mailCreateMapper::map)
                .map(mailRepository::save)
                .get();

        if (status == 1) {
            writheCache(mailCreateDto);
        }
        return status;
    }

    @Override
    public List<MailReadDto> getAllMails() {
        return findAll()
                .stream()
                .map(mailReadMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MailReadDto> getByUniqMessage(String uniqueMessage) {
        return findAll()
                .stream()
                .filter(mail -> uniqueMessage.equals(mail.getUniqueMessage()))
                .map(mailReadMapper::map)
                .findFirst();
    }

    @Override
    public int checkExistMail(String uniqueMessage) {
        return findAll()
                .stream()
                .filter(elem -> uniqueMessage.equals(elem.getUniqueMessage()))
                .toList()
                .size();
    }

    @Override
    public long getMailIdByUniqueMessage(String uniqueMessage) {
        return findAll()
                .stream()
                .filter(element -> uniqueMessage.equals(element.getUniqueMessage()))
                .map(Mail::getId)
                .findFirst()
                .get();
    }

    @Cacheable("mails-cache")
    @Override
    public List<Mail> findAll() {
        return mailRepository.findAll();
    }

    private void writheCache(MailCreateDto mailCreateDto) {
        cacheManager
                .getCache("mails-cache")
                .put("mails-cache", Optional.of(mailCreateDto).map(mailCreateMapper::map));
    }
}