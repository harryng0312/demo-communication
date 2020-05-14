package org.harryng.demo.entrypoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class CustomUserService implements UserDetailsService {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> roles = new ArrayList<>();
        String passwd = (String) ((CustomAuthenticationProvider) authenticationProvider).userPasswd.get(username)[0];
        roles.add((GrantedAuthority) ((CustomAuthenticationProvider) authenticationProvider).userPasswd.get(username)[1]);
        UserDetails userDetails = new User(username, passwd, true, true, true, true, roles);
        return null;
    }
}
