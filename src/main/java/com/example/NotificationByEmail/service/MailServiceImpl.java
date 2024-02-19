package com.example.NotificationByEmail.service;

import com.example.NotificationByEmail.dto.MailCreateDto;
import com.example.NotificationByEmail.dto.MailReadDto;
import com.example.NotificationByEmail.entity.Mail;
import com.example.NotificationByEmail.handler.exception.*;
import com.example.NotificationByEmail.mapper.MailCreateMapper;
import com.example.NotificationByEmail.mapper.MailReadMapper;
import com.example.NotificationByEmail.repository.MailRepository;
import com.example.NotificationByEmail.service.interfaces.GroupService;
import com.example.NotificationByEmail.service.interfaces.MailService;
import com.example.NotificationByEmail.service.interfaces.TemplateService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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
    private final TemplateService templateService;
    private final GroupService groupService;
    private final CacheManager cacheManager;

    @Override
    public void save(MailCreateDto mailCreateDto) {
        checkExistMail(mailCreateDto.getUniqueMessage());
        checkExistTemplate(mailCreateDto.getTemplateId());
        checkExistGroup(mailCreateDto.getGroupUser());
        int status = Optional
                .of(mailCreateDto)
                .map(mailCreateMapper::map)
                .map(mailRepository::save)
                .get();

        if (status == 1) {
            writheCache(mailCreateDto);
        }
    }



    @SneakyThrows
    @Override
    public List<MailReadDto> getAllMails() {
        List<MailReadDto> mailReadDtoList = findAll().stream()
                .map(mailReadMapper::map)
                .collect(Collectors.toList());

        if(mailReadDtoList.isEmpty())
            throw new MailListIsEmptyException();
        return mailReadDtoList;
    }

    @Override
    @SneakyThrows
    public MailReadDto getByUniqMessage(String uniqueMessage) {
        Optional<MailReadDto> mailReadDto = findAll()
                                            .stream()
                                            .filter(mail -> uniqueMessage.equals(mail.getUniqueMessage()))
                                            .map(mailReadMapper::map)
                                            .findFirst();
        if (mailReadDto.isPresent())
            return mailReadDto.get();
         else
            throw new MailNotFoundException();


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

    @SneakyThrows
    private void checkExistMail(String uniqueMessage) {
        int isExist =  findAll().stream()
                .filter(elem -> uniqueMessage.equals(elem.getUniqueMessage()))
                .toList()
                .size();
        if (isExist > 0)
            throw new UniqueMessageExistException();
    }

    @SneakyThrows
    private void checkExistTemplate(int id){
       if (templateService.checkExistTemplate(id) == 0)
           throw  new TemplateNotFoundException();
    }

    @SneakyThrows
    private void checkExistGroup(int id) {
        if (groupService.checkExistGroup(id) == 0)
            throw new GroupNotFoundException();
    }
}