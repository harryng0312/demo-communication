package org.harryng.demo.aop;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.harryng.demo.base.dto.ResponseCode;
import org.harryng.demo.base.dto.ResponseWrapper;
import org.harryng.demo.base.dto.SessionHolder;

@Slf4j
public class SessionWrapperAspect {
    @Resource
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;

    public Object around(ProceedingJoinPoint jp) throws Throwable {
        if(jp.getArgs().length > 1 && jp.getArgs()[0] instanceof SessionHolder){
            log.info("request:{}", request.getQueryString());
            final SessionHolder sessionHolder = SessionHolder.ANONYMOUS;
//            sessionHolder.setUserId();
            jp.getArgs()[0] = sessionHolder;
            return jp.proceed();
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return ResponseWrapper.<String>builder().code(ResponseCode.COMMON_ERROR);
        }
    }
}
