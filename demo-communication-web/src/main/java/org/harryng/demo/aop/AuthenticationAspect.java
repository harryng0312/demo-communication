package org.harryng.demo.aop;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.harryng.demo.impl.auth.service.AuthService;
import org.harryng.demo.api.util.SessionHolder;
import org.harryng.demo.api.exception.CodedException;

@Slf4j
public class AuthenticationAspect {

    @Resource
    private AuthService authService;

    public Object around(ProceedingJoinPoint jp) throws Throwable {
        if (jp.getArgs().length > 1) {
            log.info("+++++ auth {}.{}", jp.getTarget().getClass().getSimpleName(), jp.getSignature().getName());
            final SessionHolder sessionHolder = (SessionHolder) jp.getArgs()[0];
            if(sessionHolder !=null && authService.isValidSession(sessionHolder.getUserId(), sessionHolder.getSessionId())) {
                final Object result = jp.proceed();
                log.info("----- auth {}.{}", jp.getTarget().getClass().getSimpleName(), jp.getSignature().getName());
                return result;
            }
            log.info("----- auth {}.{}", jp.getTarget().getClass().getSimpleName(), jp.getSignature().getName());
            throw new CodedException(CodedException.NO_AUTH);
        } else {
            throw new Exception("Can not apply authentication for " + jp.getTarget().getClass().getSimpleName() + "." + jp.getSignature().getName());
        }
    }
}
