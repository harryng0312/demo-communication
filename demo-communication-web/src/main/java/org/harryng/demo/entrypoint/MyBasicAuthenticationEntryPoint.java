package org.harryng.demo.entrypoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
    static Logger logger = LoggerFactory.getLogger(MyBasicAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.addHeader("WWW-Authenticate", "Basic realm=\"" + getRealmName() + "\"");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        writer.println("HTTP Status 401 - " + authException.getMessage());
        logger.info("WEB ERROR: HTTP Status 401 - " + authException.getMessage());
    }

    @Override
    public void afterPropertiesSet() {
        setRealmName("demo-communication-realm");
        super.afterPropertiesSet();
    }
}
