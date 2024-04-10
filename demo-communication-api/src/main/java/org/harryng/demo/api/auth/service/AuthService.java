package org.harryng.demo.api.auth.service;

import org.harryng.demo.api.user.entity.UserImpl;

import java.util.Optional;

public interface AuthService {
    Optional<UserImpl> loginByUsernamePassword(String username, String password) throws Exception;
    boolean isValidSession(Long userId, String sessionId) throws Exception;
}
