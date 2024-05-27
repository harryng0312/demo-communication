package org.harryng.demo;

import org.springframework.boot.SpringApplication;
//import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
@SpringBootApplication
@ImportResource({"classpath:spring-cfg.xml"})
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
//public class Application {//implements ApplicationContextInitializer<ConfigurableApplicationContext> {

//    @Override
//    public void initialize(ConfigurableApplicationContext applicationContext) {
//        final ConfigurableEnvironment env = applicationContext.getEnvironment();
//        final Map<String, Object> customerProp = new HashMap<>();
//        System.out.println("POD_NAME:" + System.getenv("POD_NAME"));
//        customerProp.put("POD_NAME", System.getenv("POD_NAME"));
//        customerProp.put("POD_UID", System.getenv("POD_UID"));
//        env.getPropertySources().addFirst(new MapPropertySource("customProperties", customerProp));
//    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
//        SpringApplication application = new SpringApplication(Application.class);
//        application.addInitializers(new Application());
//        application.run(args);
    }
}
