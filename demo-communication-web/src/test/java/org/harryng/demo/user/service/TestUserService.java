package org.harryng.demo.user.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.harryng.demo.main.Application;
import org.harryng.demo.user.pojo.entity.UserImpl;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.*;


@SpringBootTest(classes = Application.class)
@Import(Application.class)
@Slf4j
public class TestUserService {

//    static Logger logger = LoggerFactory.getLogger(TestUserService.class);

    @Resource
    private ApplicationContext applicationContext;

    @Resource
    private UserService userService;

    @Test
    public void testAddUser() throws Exception {
        final LocalDateTime now = LocalDateTime.now();
        final UserImpl user = new UserImpl();
        user.setId(1L);
        user.setUsername("username01");
        user.setPasswd("passwd01");
        user.setScreenName("screen01");
        user.setDob(now);
        user.setPasswdEncryptedMethod("plain");

        user.setCreatedDate(now);
        user.setModifiedDate(now);
        user.setStatus("active");
        int rs = userService.add(user);
        log.info("Add {} record(s)", rs);
    }

    @Test
    public void testGetUser() throws Exception {
        final var noOfThread = 5;
        final var noOfReq = 5;
//        final var executorService = Executors.newFixedThreadPool(noOfThread);
        final var executorService = Executors.newVirtualThreadPerTaskExecutor();
        final var futures = new ArrayList<Future<UserImpl>>(noOfReq);
        for(var i = 0; i < noOfReq; i++){
            final var fut = executorService.submit(() -> {
                final var userService1 = applicationContext.getBean(UserService.class);
                final var reqId = UUID.randomUUID().toString();
                log.info("Call ID: {}", reqId);
                final var user = userService1.getById(1L);
                user.setScreenName(reqId);
                log.info("Call {} - User:{}", user.getScreenName(), user.getUsername());
                return user;
            });
            futures.add(fut);
        }
        futures.forEach(o -> {
            try {
                final var user = o.get();
                log.info("Result {} Username:{}", user.getScreenName(), user.getUsername());
            } catch (InterruptedException | ExecutionException e) {
                log.error(e.getMessage(), e);
            }
        });
        executorService.close();
    }
}
