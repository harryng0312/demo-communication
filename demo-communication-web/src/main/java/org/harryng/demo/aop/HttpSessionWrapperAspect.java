package org.harryng.demo.aop;

import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.harryng.demo.api.constant.RequestParams;
import org.harryng.demo.api.constant.ResponseCode;
import org.harryng.demo.api.util.ResponseWrapper;
import org.harryng.demo.api.util.SessionHolder;
import org.harryng.demo.api.util.SessionUtil;

import java.util.UUID;

@Slf4j
public class HttpSessionWrapperAspect {
    @Resource
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;

    public Object around(ProceedingJoinPoint jp) throws Throwable {
        if (jp.getArgs().length > 1 && jp.getArgs()[0] instanceof SessionHolder) {
//            log.info("request:{}", request.getQueryString());
            final String token = SessionUtil.getToken(request);
            final SessionHolder sessionHolder = SessionUtil.getSessionHolderFromAccessToken(token);
//            sessionHolder.setUserId();
            jp.getArgs()[0] = sessionHolder;
            log.info("+++++ wrapper {}.{}", jp.getTarget().getClass().getSimpleName(), jp.getSignature().getName());
            final Object result = jp.proceed(jp.getArgs());
            log.info("----- wrapper {}.{}", jp.getTarget().getClass().getSimpleName(), jp.getSignature().getName());
            final String jwt;
            if (jp.getArgs()[0] != null && jp.getArgs()[0] instanceof SessionHolder sessionHolderRes) {
                final UUID uuid = UUID.randomUUID();
                jwt = SessionUtil.createJwtToken(sessionHolderRes, false, 900, uuid.toString());
                final Cookie cookie = new Cookie(RequestParams.PARAM_ACCESS_TOKEN, jwt);
                cookie.setHttpOnly(true);
//                cookie.setSecure(true);
                response.addCookie(cookie);
            }
//            if(result instanceof ResponseWrapper<?> responseWrapper){
//                return responseWrapper;
//            }
//            return ResponseWrapper.builder()
//                    .code(ResponseCode.SUCCESS)
//                    .data(result)
//                    .build();
            return result;
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return ResponseWrapper.<String>builder().code(ResponseCode.COMMON_ERROR);
        }
    }
}
