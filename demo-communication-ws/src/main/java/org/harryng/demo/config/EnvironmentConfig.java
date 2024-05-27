package org.harryng.demo.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@Slf4j
public class EnvironmentConfig {
    @Resource
    private Environment env;

    @PostConstruct
    public void init() {
        String podName = env.getProperty("POD_NAME", "");
        String podId = env.getProperty("POD_ID", "");
        log.info("POD_NAME:{}, POD_ID:{}", podName, podId);
        System.setProperty("POD_NAME", podName);
        System.setProperty("POD_ID", podId);
    }
}
