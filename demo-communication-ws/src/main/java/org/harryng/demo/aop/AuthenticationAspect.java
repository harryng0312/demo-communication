package org.harryng.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;

@Slf4j
public class AuthenticationAspect {

    public Object around(ProceedingJoinPoint jp) throws Throwable {
//        if (jp.getArgs().length > 1) {
//            log.info("----- auth {}.{} -----", jp.getTarget().getClass().getSimpleName(), jp.getSignature().getName());
//            final SessionHolder sessionHolder = (SessionHolder) jp.getArgs()[0];
//            if(authService.isValidSession(sessionHolder.getUserId(), sessionHolder.getSessionId())) {
//                final Object result = jp.proceed();
//                log.info("+++++ auth {}.{} +++++", jp.getTarget().getClass().getSimpleName(), jp.getSignature().getName());
//                return result;
//            }
//            log.info("+++++ auth {}.{} +++++", jp.getTarget().getClass().getSimpleName(), jp.getSignature().getName());
//            throw new CodedException(CodedException.NO_AUTH);
//        } else {
//            throw new Exception("Can not apply authentication for " + jp.getTarget().getClass().getSimpleName() + "." + jp.getSignature().getName());
//        }
        return jp.proceed();
    }
}
