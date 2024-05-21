package org.harryng.demo.impl.auth.service;

import org.harryng.demo.impl.auth.dto.AuthenticationInfo;

public interface AuthService {
    AuthenticationInfo loginByUsernamePassword(String username, String password) throws Exception;
    boolean isValidSession(Long userId, String sessionId) throws Exception;
}
