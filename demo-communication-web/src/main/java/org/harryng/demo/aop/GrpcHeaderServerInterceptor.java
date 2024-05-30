package org.harryng.demo.aop;

import io.grpc.*;
import lombok.extern.slf4j.Slf4j;
import org.harryng.demo.api.constant.RequestParams;

@Slf4j
public class GrpcHeaderServerInterceptor implements ServerInterceptor {

    public static final Context.Key<String> HEADER_AUTHORIZATION = Context.key(RequestParams.HEADER_AUTHORIZATION);

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        String customHeaderValue = headers.get(Metadata.Key.of(RequestParams.HEADER_AUTHORIZATION, Metadata.ASCII_STRING_MARSHALLER));
        Context context = Context.current().withValue(HEADER_AUTHORIZATION, customHeaderValue);
        final long startTime = System.currentTimeMillis();
        log.info("+++++ Start: {} +++++", call.getMethodDescriptor().getBareMethodName());
        final var result = Contexts.interceptCall(context, call, headers, next);
        final long endTime = System.currentTimeMillis();
        log.info("----- End: {} ----- in: {}ms", call.getMethodDescriptor().getBareMethodName(), (endTime - startTime));
        return result;
    }
}