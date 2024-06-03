package org.harryng.demo.aop;

import io.grpc.*;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.harryng.demo.api.constant.RequestParams;
import org.harryng.demo.api.util.SessionHolder;
import org.harryng.demo.api.util.SessionUtil;
import org.harryng.demo.impl.auth.service.AuthService;

@Slf4j
public class AuthenticationGrpcInterceptor implements ServerInterceptor {

    public static final Context.Key<String> HEADER_AUTHORIZATION = Context.key(RequestParams.HEADER_AUTHORIZATION);

    @Resource
    private AuthService authService;

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        final long startTime = System.currentTimeMillis();
        log.info("+++++ Auth start: {}", call.getMethodDescriptor().getBareMethodName());
        final String token = SessionUtil.getToken(headers);
        ServerCall.Listener<ReqT> result;
        try {
            final SessionHolder sessionHolder = SessionUtil.getSessionHolderFromAccessToken(token);
            if (authService.isValidSession(sessionHolder.getUserId(), sessionHolder.getSessionId())) {
                final Context context = Context.current().withValue(HEADER_AUTHORIZATION, token);
                result = Contexts.interceptCall(context, call, headers, next);
            } else {
                // return error
                call.close(Status.UNAUTHENTICATED.withDescription("Invalid or missing token"), new Metadata());
                result = new ServerCall.Listener<>() {
                };
            }
        } catch (Exception e) {
            call.close(Status.UNAUTHENTICATED.withDescription("Invalid or missing token"), new Metadata());
            result = new ServerCall.Listener<>() {
            };
            log.error(e.getMessage(), e);
        }
        final long endTime = System.currentTimeMillis();
        log.info("----- Auth end: {} in: {} ms", call.getMethodDescriptor().getBareMethodName(), (endTime - startTime));
        return result;
    }
}