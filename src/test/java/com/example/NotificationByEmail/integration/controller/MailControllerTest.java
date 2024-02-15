package com.example.NotificationByEmail.integration.controller;

import com.example.NotificationByEmail.dto.MailCreateDto;
import com.example.NotificationByEmail.integration.TestFather;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
@AutoConfigureMockMvc
public class MailControllerTest extends TestFather {

    private final MockMvc mockMvc;
    private final static int ID = 1;
    private final static int SIZE_ARRAY = 6;
    private final static String UNIQUE_MESSAGE = "0000001";
    private final static String FILE = "1010101010101001";
    private final static String TYPE_FILE = "PDF";

    @Test
    @SneakyThrows
    void crete(){
        String mail = new ObjectMapper()
                .writeValueAsString(createMail());
        mockMvc.perform(post("/api/v1/mails")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mail)
        ).andExpect(status().isCreated());
        mockMvc.perform(post("/api/v1/mails")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mail)
        ).andExpect(status().isBadRequest());
    }

    @Test
    @SneakyThrows
    void getAll(){
        mockMvc.perform(get("/api/v1/mails")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(SIZE_ARRAY))
                .andExpect(jsonPath("$[0].uniqueMessage").value(UNIQUE_MESSAGE))
                .andExpect(jsonPath("$[0].file").value(FILE))
                .andExpect(jsonPath("$[0].typeFile").value(TYPE_FILE))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @SneakyThrows
    void findByUniqueMessage() {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/mails/{id}", UNIQUE_MESSAGE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.uniqueMessage").value(UNIQUE_MESSAGE))
                .andExpect(jsonPath("$.file").value(FILE))
                .andExpect(jsonPath("$.typeFile").value(TYPE_FILE))
                .andExpect(status().is2xxSuccessful());
    }

    private MailCreateDto createMail(){    Map<String, String> map = new HashMap<>();
        map.put("name", "name1");
        map.put("message", "message1");
        MailCreateDto mailCreateDto = new MailCreateDto();
        mailCreateDto.setUniqueMessage("111111111111111111");
        mailCreateDto.setGroupUser(ID);
        mailCreateDto.setTemplateId(ID);
        mailCreateDto.setFile("1110010101001101");
        mailCreateDto.setTypeFile("PDF");
        mailCreateDto.setData(map);
        return mailCreateDto;
    }
}