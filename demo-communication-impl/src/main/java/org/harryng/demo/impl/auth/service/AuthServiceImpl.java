package org.harryng.demo.impl.auth.service;

import jakarta.annotation.Resource;
import org.harryng.demo.api.auth.dto.AuthenticationInfo;
import org.harryng.demo.api.auth.service.AuthService;
import org.harryng.demo.api.base.dto.ResponseWrapper;
import org.harryng.demo.api.base.dto.SessionHolder;
import org.harryng.demo.api.constant.ResponseCode;
import org.harryng.demo.api.exception.CodedException;
import org.harryng.demo.api.user.entity.UserImpl;
import org.harryng.demo.api.user.service.UserService;
import org.harryng.demo.api.util.SecurityUtil;
import org.harryng.demo.impl.util.SessionUtil;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

public class AuthServiceImpl implements AuthService {

    @Resource
    private UserService userService;

    @Override
    public AuthenticationInfo loginByUsernamePassword(String username, String password) throws Exception {
        if (password == null || password.isBlank()) {
            throw new CodedException(ResponseCode.AUTH_PASSWD_NOT_MATCH, "Password is empty");
        }
        final Optional<UserImpl> userOpt = userService.getByUsername(username, Collections.emptyMap());
        if (userOpt.isPresent()) {
            final UserImpl user = userOpt.get();
            if ("plain".equalsIgnoreCase(user.getPasswdEncryptedMethod())) {
                if (!password.equals(user.getPasswd())) {
                    throw new CodedException(ResponseCode.AUTH_PASSWD_NOT_MATCH, "Username or Password is not matched");
                }
            } else {
                byte[] inputPasswdBin = password.getBytes(StandardCharsets.UTF_8);
                byte[] inputHashedPasswdBin = SecurityUtil.hashMessage(user.getPasswdEncryptedMethod(), inputPasswdBin);
                String inputHashedPasswd = new String(inputHashedPasswdBin);
                if (!inputHashedPasswd.equals(user.getPasswd())) {
                    throw new CodedException(ResponseCode.AUTH_PASSWD_NOT_MATCH, "Username or Password is not matched");
                }
            }

            // create SessionHolder
            final int durationInSecond = 900;
            final LocalDateTime now = LocalDateTime.now();
            final SessionHolder sessionHolder = SessionHolder.builder()
                    .sessionId(UUID.randomUUID().toString())
                    .userId(user.getId())
                    .username(user.getUsername())
                    .notBefore(now)
                    .validity(now.plusSeconds(durationInSecond))
                    .build();

            final String token = SessionUtil.getJwtToken(sessionHolder, true, durationInSecond, UUID.randomUUID().toString());
            final AuthenticationInfo authenticationInfo = new AuthenticationInfo();
            authenticationInfo.setId(sessionHolder.getSessionId());
            authenticationInfo.setUsername(user.getUsername());
            authenticationInfo.setRequestTime(LocalDateTime.now());
            authenticationInfo.setToken(token);
            return authenticationInfo;
        }
        throw new CodedException(ResponseCode.AUTH_CANNOT_FIND_USERNAME, "User is not found");
    }

    @Override
    public boolean isValidSession(Long userId, String sessionId) throws Exception {
        return userId != null && userId > 0;
    }
}
