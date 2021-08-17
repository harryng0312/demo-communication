package org.harryng.demo.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
@ImportResource({"classpath:spring-cfg.xml"})
public class Application extends SpringBootServletInitializer {

    static Logger logger = LoggerFactory.getLogger(Application.class);

//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        super.onStartup(servletContext);
//        logger.info("===== web init =====");
//        Filter keycloakOIDCFilter = new KeycloakOIDCFilter();
//        FilterRegistration filterRegistration = servletContext.addFilter("kl-filter", keycloakOIDCFilter);
//        filterRegistration.setInitParameter(KeycloakOIDCFilter.CONFIG_FILE_PARAM, "/WEB-INF/auth/keycloak.json");
//        filterRegistration.getUrlPatternMappings().add("/api");
//    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
