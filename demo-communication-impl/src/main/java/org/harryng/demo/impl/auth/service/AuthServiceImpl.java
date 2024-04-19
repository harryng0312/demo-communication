package org.harryng.demo.impl.auth.service;

import jakarta.annotation.Resource;
import org.harryng.demo.api.auth.service.AuthService;
import org.harryng.demo.api.base.dto.ResponseWrapper;
import org.harryng.demo.api.constant.ResponseCode;
import org.harryng.demo.api.user.entity.UserImpl;
import org.harryng.demo.api.user.service.UserService;
import org.harryng.demo.api.util.SecurityUtil;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Optional;

public class AuthServiceImpl implements AuthService {

    @Resource
    private UserService userService;

    @Override
    public ResponseWrapper<UserImpl> loginByUsernamePassword(String username, String password) throws Exception {
        if (password == null || password.isBlank()) {
//            throw new Exception("Password is not valid");
            return ResponseWrapper.<UserImpl>builder()
                    .code(ResponseCode.AUTH_PASSWD_NOT_MATCH)
                    .msg("Password is not match")
                    .build();
        }
        final ResponseWrapper<UserImpl> userOpt = userService.getByUsername(username, Collections.emptyMap());
        if (userOpt.getData()!=null) {
            final UserImpl user = userOpt.getData();
            if ("plain".equalsIgnoreCase(user.getPasswdEncryptedMethod())) {
                if (!password.equals(user.getPasswd())) {
                    return ResponseWrapper.<UserImpl>builder()
                            .code(ResponseCode.AUTH_PASSWD_NOT_MATCH)
                            .msg("Username or Password is not matched")
                            .build();
                }
            } else {
                byte[] inputPasswdBin = password.getBytes(StandardCharsets.UTF_8);
                byte[] inputHashedPasswdBin = SecurityUtil.hashMessage(user.getPasswdEncryptedMethod(), inputPasswdBin);
                String inputHashedPasswd = new String(inputHashedPasswdBin);
                if (!inputHashedPasswd.equals(user.getPasswd())) {
                    return ResponseWrapper.<UserImpl>builder()
                            .code(ResponseCode.AUTH_PASSWD_NOT_MATCH)
                            .msg("Username or Password is not matched")
                            .build();
                }
            }
        } else {
            return ResponseWrapper.<UserImpl>builder()
                    .code(ResponseCode.AUTH_CANNOT_FIND_USERNAME)
                    .msg("User is not found")
                    .build();
        }
        return userOpt;
    }

    @Override
    public boolean isValidSession(Long userId, String sessionId) throws Exception {
        return true;
    }
}
