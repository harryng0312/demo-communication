package org.harryng.demo.controller.graphql;

import org.harryng.demo.api.util.ValidationResult;
import org.harryng.demo.impl.auth.dto.AuthenticationInfo;
import org.harryng.demo.impl.auth.service.AuthService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Controller;

@Controller
public class AuthenticationResolver {
    private final AuthService authService;
    public AuthenticationResolver(AuthService authService) {
        this.authService = authService;
    }

    public ValidationResult<AuthenticationInfo> login(@Argument String username, @Argument String password) throws Exception {
        return authService.loginByUsernamePassword(username, password);
    }
}
