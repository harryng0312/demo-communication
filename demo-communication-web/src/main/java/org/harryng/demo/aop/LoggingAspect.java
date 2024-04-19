package org.harryng.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Slf4j
public class LoggingAspect {
    public void before(){
        log.info("-----");
    }

    public void after(){
        log.info("+++++");
    }

    public Object around(ProceedingJoinPoint jp) throws Throwable{
        final LocalDateTime start = LocalDateTime.now();
        log.info("----- {}.{}[{}] -----", jp.getTarget().getClass().getSimpleName(), jp.getSignature().getName(), jp.getArgs());
        final Object result = jp.proceed();
        final LocalDateTime finish = LocalDateTime.now();
        log.info("+++++ {}.{} +++++ {} millisecond(s)", jp.getTarget().getClass().getSimpleName(), jp.getSignature().getName(),
                start.until(finish, ChronoUnit.MILLIS));
        return result;
    }
}
