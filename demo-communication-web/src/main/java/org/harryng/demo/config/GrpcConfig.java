package org.harryng.demo.config;

import io.grpc.ServerInterceptor;
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;
import org.harryng.demo.aop.AuthenticationGrpcInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class GrpcConfig {

//    @Bean
//    @GrpcGlobalServerInterceptor
//    @Order(10)
//    public ServerInterceptor getLoggingGrpcInterceptor() {
//        return new LoggingGrpcInterceptor();
//    }

    @Bean
    @GrpcGlobalServerInterceptor
    @Order(20)
    public ServerInterceptor getAuthenticationGrpcInterceptor() {
        return new AuthenticationGrpcInterceptor();
    }


}
