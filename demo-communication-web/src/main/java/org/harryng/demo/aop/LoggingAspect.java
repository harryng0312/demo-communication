package org.harryng.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;

@Slf4j
public class LoggingAspect {
    public void before(){
        log.info("-----");
    }

    public void after(){
        log.info("+++++");
    }

    public Object around(ProceedingJoinPoint jp) throws Throwable{
        log.info("----- {}.{} -----", jp.getTarget().getClass().getSimpleName(), jp.getSignature().getName());
        final Object result = jp.proceed();
        log.info("+++++ {}.{} +++++", jp.getTarget().getClass().getSimpleName(), jp.getSignature().getName());
        return result;
    }
}
