package org.harryng.demo.controller.grpc;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.harryng.demo.api.util.ResponseWrapper;
import org.harryng.demo.api.util.ValidationResult;
import org.harryng.demo.controller.grpc.auth.AuthControllerGrpc;
import org.harryng.demo.controller.grpc.auth.LoginReq;
import org.harryng.demo.controller.grpc.auth.LoginRes;
import org.harryng.demo.impl.auth.dto.AuthenticationInfo;
import org.harryng.demo.impl.auth.service.AuthService;
import org.harryng.demo.impl.user.service.UserService;
import org.springframework.stereotype.Component;

@Component
@GrpcService
@RequiredArgsConstructor
@Slf4j
public class AuthGrpcController extends AuthControllerGrpc.AuthControllerImplBase {

    protected final AuthService authService;
    protected final UserService userService;

    @Override
    public void login(LoginReq request, StreamObserver<LoginRes> responseObserver) {
        try {
            final ValidationResult<AuthenticationInfo> authenticationInfo = authService.loginByUsernamePassword(request.getUsername(), request.getPassword());
            final ResponseWrapper<AuthenticationInfo> res = ResponseWrapper.<AuthenticationInfo>builder().data(authenticationInfo.getValue()).build();
//        return TextUtil.objToJson(res);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            responseObserver.onError(e);
        } finally {
            responseObserver.onCompleted();
        }
    }
}
