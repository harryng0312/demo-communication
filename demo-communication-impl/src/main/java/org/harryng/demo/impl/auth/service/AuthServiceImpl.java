package org.harryng.demo.impl.auth.service;

import lombok.RequiredArgsConstructor;
import org.harryng.demo.api.constant.ResponseCode;
import org.harryng.demo.api.exception.CodedException;
import org.harryng.demo.api.util.SecurityUtil;
import org.harryng.demo.api.util.SessionHolder;
import org.harryng.demo.impl.auth.dto.AuthenticationInfo;
import org.harryng.demo.impl.user.dto.UserDto;
import org.harryng.demo.impl.user.service.UserService;
import org.harryng.demo.api.util.SessionUtil;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    @Override
    public AuthenticationInfo loginByUsernamePassword(String username, String password) throws Exception {
        if (password == null || password.isBlank()) {
            throw new CodedException(ResponseCode.AUTH_PASSWD_NOT_MATCH, "Password is empty");
        }
        final Optional<UserDto> userOpt = userService.getByUsername(username, Collections.emptyMap());
        if (userOpt.isPresent()) {
            final UserDto user = userOpt.get();
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

            final String token = SessionUtil.createJwtToken(sessionHolder, true, durationInSecond, UUID.randomUUID().toString());
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
