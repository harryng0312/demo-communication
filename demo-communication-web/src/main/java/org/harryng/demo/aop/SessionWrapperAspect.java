package org.harryng.demo.aop;

import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.harryng.demo.api.constant.RequestParam;
import org.harryng.demo.api.constant.ResponseCode;
import org.harryng.demo.api.base.dto.ResponseWrapper;
import org.harryng.demo.api.base.dto.SessionHolder;
import org.harryng.demo.utillities.SessionUtil;

import java.util.UUID;

@Slf4j
public class SessionWrapperAspect {
    @Resource
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;

    public Object around(ProceedingJoinPoint jp) throws Throwable {
        if (jp.getArgs().length > 1 && jp.getArgs()[0] instanceof SessionHolder) {
            log.info("request:{}", request.getQueryString());
            final SessionHolder sessionHolder = SessionUtil.getSessionHolderFromHttpRequest(
                    request.getHeader(RequestParam.HEADER_AUTHORIZATION),
                    request.getParameter(RequestParam.PARAM_ACCESS_TOKEN)
            );
//            sessionHolder.setUserId();
            jp.getArgs()[0] = sessionHolder;
            final Object result = jp.proceed();
            final String jwt;
            if (jp.getArgs()[0] != null && jp.getArgs()[0] instanceof SessionHolder sessionHolderRes) {
                final UUID uuid = UUID.randomUUID();
                jwt = SessionUtil.getJwtToken(sessionHolderRes, false, 900, uuid.toString());
                final Cookie cookie = new Cookie(RequestParam.PARAM_ACCESS_TOKEN, jwt);
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
