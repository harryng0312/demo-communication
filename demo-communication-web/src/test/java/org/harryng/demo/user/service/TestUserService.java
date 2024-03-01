package org.harryng.demo.user.service;

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
import java.util.Calendar;
import java.util.Date;


@SpringBootTest(classes = Application.class)
@Import(Application.class)
@Slf4j
public class TestUserService {

//    static Logger logger = LoggerFactory.getLogger(TestUserService.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
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
        final UserImpl user = userService.getById(1L);
        log.info("User:" + user.getUsername());
    }
}
