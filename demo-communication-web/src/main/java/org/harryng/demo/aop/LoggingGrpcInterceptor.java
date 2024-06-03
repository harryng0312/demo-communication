package org.harryng.demo.aop;

import io.grpc.*;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class LoggingGrpcInterceptor implements ServerInterceptor {

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        final long startTime = System.currentTimeMillis();
        final UUID uuid = UUID.randomUUID();
        log.info("+++++ Start: {}", uuid);
        final var result = next.startCall(call, headers);
        final long endTime = System.currentTimeMillis();
        log.info("----- End: {} in: {} ms", uuid, (endTime - startTime));
        return result;
    }
}