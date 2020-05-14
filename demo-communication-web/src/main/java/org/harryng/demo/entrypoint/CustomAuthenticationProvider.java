package org.harryng.demo.entrypoint;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    public Map<String, Object[]> userPasswd = new HashMap<>();

    public void init() {
        userPasswd.put("admin", new Object[]{"12345678", new SimpleGrantedAuthority("ROLE_ADMIN")});
        userPasswd.put("user", new Object[]{"12345678", new SimpleGrantedAuthority("ROLE_USER")});
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());
        UsernamePasswordAuthenticationToken result = null;
        if (userPasswd.get(name) != null && password.equals(userPasswd.get(name)[0])) {
            // use the credentials
            // and authenticate against the third-party system
            List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
            grantedAuthorityList.add((SimpleGrantedAuthority) userPasswd.get(name)[1]);
            result = new UsernamePasswordAuthenticationToken(name, password, grantedAuthorityList);
        }
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
