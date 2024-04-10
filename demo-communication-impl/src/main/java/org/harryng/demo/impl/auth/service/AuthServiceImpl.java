package org.harryng.demo.impl.auth.service;

import jakarta.annotation.Resource;
import org.harryng.demo.api.auth.service.AuthService;
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
    public Optional<UserImpl> loginByUsernamePassword(String username, String password) throws Exception {
        Optional<UserImpl> userOpt = userService.getByUsername(username, Collections.emptyMap());
        if (password == null) {
            throw new Exception("Password is not valid");
        }
        if (userOpt.isPresent()) {
            final UserImpl user = userOpt.get();
            if ("plain".equalsIgnoreCase(user.getPasswdEncryptedMethod())) {
                if (!password.equals(user.getPasswd())) {
                    throw new Exception("Username or Password is not matched");
                }
            } else {
                byte[] inputPasswdBin = password.getBytes(StandardCharsets.UTF_8);
                byte[] inputHashedPasswdBin = SecurityUtil.hashMessage(user.getPasswdEncryptedMethod(), inputPasswdBin);
                String inputHashedPasswd = new String(inputHashedPasswdBin);
                if (!inputHashedPasswd.equals(user.getPasswd())) {
                    throw new Exception("Password is not matched");
                }
            }
        } else {
            throw new Exception("User is not found");
        }
        return userOpt;
    }

    @Override
    public boolean isValidSession(Long userId, String sessionId) throws Exception {
        return false;
    }
}
