package org.harryng.demo.auth.service;

public interface AuthService {
    void loginByUsernamePassword(String username, String password) throws RuntimeException, Exception;
}
