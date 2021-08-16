package org.harryng.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class WebAppInitializer implements WebApplicationInitializer {
    @Autowired
    @Qualifier("keycloakOIDCFilter")
    protected Filter keycloakOIDCFilter;

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(keycloakOIDCFilter);
        filterRegistrationBean.addUrlPatterns("/api");
    }
}
