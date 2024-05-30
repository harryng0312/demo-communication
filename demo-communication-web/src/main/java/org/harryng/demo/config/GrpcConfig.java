package org.harryng.demo.config;

import io.grpc.ServerInterceptor;
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;
import org.harryng.demo.aop.GrpcHeaderServerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfig {
    @Bean
    @GrpcGlobalServerInterceptor
    public ServerInterceptor headerServerInterceptor() {
        return new GrpcHeaderServerInterceptor();
    }
}
