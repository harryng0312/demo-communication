package org.harryng.demo.user.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.harryng.demo.api.base.dto.ResponseWrapper;
import org.harryng.demo.api.base.dto.SessionHolder;
import org.harryng.demo.api.user.persistence.UserPersistence;
import org.harryng.demo.api.user.service.UserService;
import org.harryng.demo.main.Application;
import org.harryng.demo.impl.user.mapper.UserMapper;
import org.harryng.demo.api.user.entity.UserImpl;
import org.harryng.demo.api.user.dto.UserRequest;
import org.harryng.demo.api.user.dto.UserResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.*;
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
    @Resource
    private UserPersistence userPersistence;

    @Resource
    private UserMapper mapper;

    @Test
    public void testAddUser() throws Exception {
        final LocalDateTime now = LocalDateTime.now();
        final UserImpl user = new UserImpl();
        user.setId(1L);
        user.setUsername("username01");
        user.setPasswd("passwd01");
        user.setScreenName("screen01");
        user.setDob(now.toLocalDate());
        user.setPasswdEncryptedMethod("plain");

        user.setCreatedDate(now);
        user.setModifiedDate(now);
        user.setStatus(1);
        final UserImpl rs = userService.add(SessionHolder.builder().build(), user, Collections.emptyMap());
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
                final UserImpl userDefault = new UserImpl();
                try (var executorService = Executors.newVirtualThreadPerTaskExecutor()) {
                    final var futures = new ArrayList<Future<Optional<UserImpl>>>(noOfReq);
                    for (var i = 0; i < noOfReq; i++) {
                        final var fut = executorService.submit(() -> {

                            final var userService1 = applicationContext.getBean(UserService.class);
                            final var reqId = UUID.randomUUID().toString();
                            log.info("Call ID: {}", reqId);
                            final var user = userService1.getById(SessionHolder.builder().build(), 1L, Collections.emptyMap());
//                                userService1.getPersistence().getStatelessSession().close();
                            user.orElse(userDefault).setScreenName(reqId);
                            log.info("Call {} - User:{}", user.orElse(userDefault).getScreenName(), user.orElse(userDefault).getUsername());
                            return user;
                        });
                        futures.add(fut);
                    }
                    futures.forEach(o -> {
                        try {
                            final var user = o.get(3, TimeUnit.SECONDS);
                            log.info("Result {} Username:{}", user.orElse(userDefault).getScreenName(), user.orElse(userDefault).getUsername());
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
//        final var mapper = applicationContext.getBean(UserMapper.class);
//        final var userService = applicationContext.getBean(UserService.class);
        final var user = userService.getByUsername("username01", Collections.emptyMap());
        final var userRequest = new UserRequest();
        userRequest.setUsername("username 1");
        userRequest.setDob(LocalDateTime.now());
        userRequest.setScreenName("screen name 1");
        final var userEntity = mapper.map(userRequest);
        log.info("user entity: {}", userEntity);
        if (user.isPresent()) {
            final var userRes = mapper.map(user.get());
            final var res = ResponseWrapper.<UserResponse>builder()
                    .data(userRes)
                    .build();
            log.info("user res: {}", res);
        }
    }

    @Test
//    @Transactional
    public void testUser() throws Exception {
//        final var mapper = applicationContext.getBean(UserMapper.class);
//        final var userService = applicationContext.getBean(UserService.class);
//        final Optional<UserImpl> user = userService.getById(SessionHolder.ANONYMOUS, 1L, Collections.emptyMap());
        final Optional<UserImpl> user = userService.getById(SessionHolder.ANONYMOUS, 2L, true, true, true, Map.of());
//        user.ifPresent(value -> value.getUsergroupIds().addAll(userPersistence.getUsergroupIds(value.getId())));
        final var userRequest = new UserRequest();
        log.info("DB user entity: {}", user);
        userRequest.setUsername("username 2");
        userRequest.setDob(LocalDateTime.now());
        userRequest.setScreenName("screen name 2");
        final var userEntity = mapper.map(userRequest);
        log.info("user entity: {}", userEntity);
        final var userRes = mapper.map(user.orElseGet(UserImpl::new));
        final var res = ResponseWrapper.<UserResponse>builder()
                .data(userRes)
                .build();
        log.info("user res: {}", res);
    }
}
