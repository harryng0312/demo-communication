package org.harryng.demo.controller;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.harryng.demo.Application;
import org.harryng.demo.api.constant.RequestParams;
import org.harryng.demo.api.util.TextUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(Application.class)
@AutoConfigureMockMvc
@AutoConfigureWebTestClient
@RequiredArgsConstructor
@Slf4j
//@WebMvcTest(AuthController.class)
public class TestAuthController {
//    @Resource
//    private AuthController authController;

    private final MockMvc mockMvc;
    private final WebTestClient webClient;

    @Test
    public void testGetLogin() throws Exception {
//        final var getLogin = MockMvcRequestBuilders.get("/login");
//        mockMvc.perform(getLogin)
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("txtUsername")));
        final var resGetLogin = webClient.get().uri("/login")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();
        assert resGetLogin != null;
        log.info("Res Get Login:{}", resGetLogin.contains("txtUsername"));
    }

    @Test
    public void testPostLogin() throws Exception {
        log.info("=====");
//        assertThat(authController).isNotNull();
//        final var getLogin = MockMvcRequestBuilders.get("/login");
//        mockMvc.perform(getLogin)
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("txtUsername")));
        final var postLogin = MockMvcRequestBuilders.post("/login");
//        postLogin.accept(MediaType.APPLICATION_JSON_VALUE);
        postLogin.contentType(MediaType.APPLICATION_JSON_VALUE);
        postLogin.param("username", "username01")
                .param("password", "passwd01");
        final var resLogin = mockMvc.perform(postLogin)
//                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("username01")))
                .andReturn();
        if (resLogin.getResponse().getStatus() == 200) {
//            Thread.sleep(1000);
            final String body = resLogin.getResponse().getContentAsString();
            final var jsonNode = TextUtil.jsonToNode(body);
            if (jsonNode.get("code").asInt() == 0) {
                final var token = jsonNode.get("data").get("token").asText();
                log.info("token:{}", token);
                final var getWelcome = MockMvcRequestBuilders.get("/welcome");
                getWelcome.header(RequestParams.HEADER_AUTHORIZATION, "Bearer " + token);
                final var resWelcome = mockMvc.perform(getWelcome)
                        .andExpect(MockMvcResultMatchers.status().isOk())
//                        .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("screen01")))
                        .andReturn();
                if (resWelcome.getResponse().getStatus() == 200) {
                    log.info("welcome:{}", resWelcome.getResponse().getContentAsString().contains("screen01"));
                }
            }
        }
    }
}
