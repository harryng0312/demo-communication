package org.harryng.demo.api.auth.service;

import org.harryng.demo.api.user.entity.UserImpl;

public interface AuthService {
    UserImpl loginByUsernamePassword(String username, String password) throws Exception;
    boolean isValidSession(Long userId, String sessionId) throws Exception;
}
