package org.harryng.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Slf4j
public class LoggingAspect {
    public void before(){
        log.info("+++++");
    }

    public void after(){
        log.info("-----");
    }

    public Object around(ProceedingJoinPoint jp) throws Throwable {
        final UUID uuid = UUID.randomUUID();
        final LocalDateTime start = LocalDateTime.now();
        log.info("+++++ [REQUEST][{}]:{}.{}({}) +++++", uuid, jp.getTarget().getClass().getSimpleName(), jp.getSignature().getName(), jp.getArgs());
        try {
            return jp.proceed();
        } finally {
            final LocalDateTime finish = LocalDateTime.now();
            log.info("----- [RESPONSE][{}]: {}.{} ----- {} millisecond(s)", uuid, jp.getTarget().getClass().getSimpleName(), jp.getSignature().getName(),
                    start.until(finish, ChronoUnit.MILLIS));
        }
    }
}
