package com.example.whatsappchatbot.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WebhookControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    void hiMapsToHello() throws Exception {
        mvc.perform(post("/webhook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"from":"233000000000","text":"Hi"}
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reply").value("Hello"));
    }

    @Test
    void byeMapsToGoodbye() throws Exception {
        mvc.perform(post("/webhook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"from":"233000000000","text":"Bye"}
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reply").value("Goodbye"));
    }
}

