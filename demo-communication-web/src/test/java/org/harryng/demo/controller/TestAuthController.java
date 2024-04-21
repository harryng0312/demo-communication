package org.harryng.demo.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.harryng.demo.Application;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(
        classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Import(Application.class)
@AutoConfigureMockMvc
@Slf4j
//@WebMvcTest(AuthController.class)
public class TestAuthController {
//    @Resource
//    private AuthController authController;

    @Resource
    private MockMvc mockMvc;

    @Test
    public void testLogin() throws Exception {
        log.info("=====");
//        assertThat(authController).isNotNull();
        final var requestBuilder = MockMvcRequestBuilders.get("/login");
        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("txtUsername")));
    }
}
