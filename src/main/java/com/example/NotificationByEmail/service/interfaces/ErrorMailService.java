package com.example.NotificationByEmail.service.interfaces;

import com.example.NotificationByEmail.dto.ErrorMailCreateDto;
import com.example.NotificationByEmail.dto.ErrorMailReadDto;
import com.example.NotificationByEmail.entity.ErrorMail;
import java.util.List;

public interface ErrorMailService {
    void save(ErrorMailCreateDto errorMailCreateDto);

    List<ErrorMailReadDto> getByIdMail(long idMail);

    List<ErrorMail> findAll();
}