package org.harryng.demo.auth.service;

import org.harryng.demo.user.entity.UserImpl;

public interface AuthService {
    UserImpl loginByUsernamePassword(String username, String password) throws Exception;
    boolean isValidSession(Long userId, String sessionId) throws Exception;
}
