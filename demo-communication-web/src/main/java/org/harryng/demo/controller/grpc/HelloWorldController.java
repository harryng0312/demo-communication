package org.harryng.demo.controller.grpc;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
@GrpcService
@Slf4j
public class HelloWorldController extends HelloWorldServiceGrpc.HelloWorldServiceImplBase {
    @Override
    public StreamObserver<HelloWorldRequest> sayHello(StreamObserver<HelloWorldResponse> responseObserver) {
        log.info("+++++ Connected +++++");
        return new StreamObserver<>() {
            @Override
            public void onNext(HelloWorldRequest helloWorldRequest) {
                final var name = helloWorldRequest.getName();
                log.info("from request:{}", name);
                final var item = HelloWorldResponse.newBuilder()
                        .setGreeting(MessageFormat.format("Hello {0} from server", name))
                        .build();
                responseObserver.onNext(item);
            }
            @Override
            public void onError(Throwable throwable) {
                log.error(throwable.getMessage(), throwable);
            }
            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
                log.info("----- Disconnected -----");
            }
        };
    }
}
