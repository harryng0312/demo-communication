package org.harryng.demo.config;

import org.harryng.demo.main.Application;
import org.keycloak.adapters.servlet.KeycloakOIDCFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class WebAppInitializer implements ServletContextInitializer {
//    @Autowired
//    @Qualifier("keycloakOIDCFilter")
//    protected Filter keycloakOIDCFilter;
    static Logger logger = LoggerFactory.getLogger(Application.class);

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        logger.info("===== web init =====");
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        Filter keycloakOIDCFilter = new KeycloakOIDCFilter();
        FilterRegistration filterRegistration = servletContext.addFilter("kl-filter", keycloakOIDCFilter);
        filterRegistration.setInitParameter(KeycloakOIDCFilter.CONFIG_FILE_PARAM, "/WEB-INF/auth/keycloak.json");
        filterRegistration.getUrlPatternMappings().add("/api");
    }
}
