package org.harryng.demo.user.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.harryng.demo.base.pojo.dto.ResponseWrapper;
import org.harryng.demo.main.Application;
import org.harryng.demo.user.mapper.UserMapper;
import org.harryng.demo.user.pojo.data.entity.UserImpl;
import org.harryng.demo.user.pojo.dto.UserRequest;
import org.harryng.demo.user.pojo.dto.UserResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        final var timeout = 2 * 1000L;
        final var noOfThread = 5;
        final var noOfReq = 10;
        try (var xService = Executors.newFixedThreadPool(noOfThread)) {
            xService.submit(() -> {
//                while (!xService.isTerminated() && !xService.isShutdown()) {
                    try (var executorService = Executors.newVirtualThreadPerTaskExecutor()) {
                        final var futures = new ArrayList<Future<UserImpl>>(noOfReq);
                        for (var i = 0; i < noOfReq; i++) {
                            final var fut = executorService.submit(() -> {
                                final var userService1 = applicationContext.getBean(UserService.class);
                                final var reqId = UUID.randomUUID().toString();
                                log.info("Call ID: {}", reqId);
                                final var user = userService1.getById(1L);
//                                userService1.getPersistence().getStatelessSession().close();
                                user.setScreenName(reqId);
                                log.info("Call {} - User:{}", user.getScreenName(), user.getUsername());
                                return user;
                            });
                            futures.add(fut);
                        }
                        futures.forEach(o -> {
                            try {
                                final var user = o.get(3, TimeUnit.SECONDS);
                                log.info("Result {} Username:{}", user.getScreenName(), user.getUsername());
                            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                                log.error(e.getMessage(), e);
                            }
                        });
//                    }
                }
            });
//            log.info("waiting...");
            Thread.sleep(timeout);
            xService.shutdown();
//            xService.awaitTermination();
//            xService.awaitTermination(timeout, TimeUnit.MILLISECONDS);
        }
    }

    @Test
    public void testUserDtoEntityMapper() throws Exception {
        final UserMapper mapper = applicationContext.getBean(UserMapper.class);
        final var userService = applicationContext.getBean(UserService.class);
        final var user = userService.getById(1L);
        final UserRequest userRequest = new UserRequest();
        userRequest.setUsername("username 1");
        userRequest.setDob(LocalDateTime.now());
        userRequest.setScreenName("screen name 1");
        final var userEntity = mapper.map(userRequest);
        log.info("user entity: {}", userEntity);
        final UserResponse userRes = mapper.map(user);
        final var res = ResponseWrapper.<UserResponse>builder()
                .data(userRes)
                .build();
        log.info("user res: {}", res);
    }
}
