package org.harryng.demo.api.auth.service;

import org.harryng.demo.api.auth.dto.AuthenticationInfo;

public interface AuthService {
    AuthenticationInfo loginByUsernamePassword(String username, String password) throws Exception;
    boolean isValidSession(Long userId, String sessionId) throws Exception;
}
