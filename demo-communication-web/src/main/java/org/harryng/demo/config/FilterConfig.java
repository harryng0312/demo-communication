package org.harryng.demo.config;

import org.keycloak.adapters.servlet.KeycloakOIDCFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class FilterConfig {
//    @Autowired
//    @Qualifier("keycloakOIDCFilter")
//    protected Filter keycloakOIDCFilter;
    static Logger logger = LoggerFactory.getLogger(FilterConfig.class);

    @Bean
    public FilterRegistrationBean loginRegistrationBean() {
        logger.info("===== web init =====");
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        Filter keycloakOIDCFilter = new KeycloakOIDCFilter();
        filterRegistrationBean.setName("kl-filter");
        filterRegistrationBean.setFilter(keycloakOIDCFilter);
        filterRegistrationBean.addInitParameter(KeycloakOIDCFilter.CONFIG_FILE_PARAM, "/WEB-INF/auth/keycloak.json");
        filterRegistrationBean.getUrlPatterns().add("/api");
        return filterRegistrationBean;
    }
}
