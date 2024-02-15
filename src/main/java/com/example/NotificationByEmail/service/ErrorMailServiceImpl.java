package com.example.NotificationByEmail.service;

import com.example.NotificationByEmail.dto.ErrorMailCreateDto;
import com.example.NotificationByEmail.dto.ErrorMailReadDto;
import com.example.NotificationByEmail.entity.ErrorMail;
import com.example.NotificationByEmail.mapper.ErrorMailCreateMapper;
import com.example.NotificationByEmail.mapper.ErrorMailReadMapper;
import com.example.NotificationByEmail.repository.ErrorMailRepository;
import com.example.NotificationByEmail.service.interfaces.ErrorMailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class ErrorMailServiceImpl implements ErrorMailService {

    private final ErrorMailRepository errorMailRepository;
    private final ErrorMailReadMapper errorMailReadMapper;
    private final ErrorMailCreateMapper errorMailCreateMapper;
    private final CacheManager cacheManager;

    @Override
    public void save(ErrorMailCreateDto errorMailCreateDto) {
        int status = errorMailRepository.save(errorMailCreateMapper.map(errorMailCreateDto));
        if (status == 1) {
            writheCache(errorMailCreateDto);
        }
    }

    @Override
    public List<ErrorMailReadDto> getByIdMail(long idMail) {
        return findAll()
                .stream()
                .map(errorMailReadMapper::map)
                .toList();
    }

    @Cacheable("errors-cache")
    @Override
    public List<ErrorMail> findAll() {
        return errorMailRepository.findAll();
    }

    private void writheCache(ErrorMailCreateDto errorMailCreateDto) {
        Objects.requireNonNull(cacheManager
                .getCache("errors-cache")).clear();
    }
}