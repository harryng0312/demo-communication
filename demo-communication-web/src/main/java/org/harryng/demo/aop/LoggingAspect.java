package org.harryng.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Slf4j
public class LoggingAspect {
    public void before() {
        log.info("+++++");
    }

    public void after() {
        log.info("-----");
    }

    public Object around(ProceedingJoinPoint jp) throws Throwable {
        final UUID uuid = UUID.randomUUID();
        final long start = System.currentTimeMillis();
        log.info("+++++ [REQUEST:{}]:{}.{}({})", uuid, jp.getTarget().getClass().getSimpleName(), jp.getSignature().getName(), jp.getArgs());
        try {
            final Object result = jp.proceed();
            final long finish = System.currentTimeMillis();
            log.info("----- [RESPONSE:{}]:{} in {} millisecond(s)", uuid, result, (finish - start));
            return result;
        } catch (Exception e){
            final long finish = System.currentTimeMillis();
            log.info("----- [RESPONSE:{}]:{} in {} millisecond(s)", uuid, e.getMessage(), (finish - start));
            throw e;
        }
    }
}
