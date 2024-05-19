package org.harryng.demo.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.*;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ApplicationEventListener {

    @EventListener(ApplicationStartedEvent.class)
    public void onApplicationStarted(ApplicationStartedEvent event) {
        log.info("Application is Started!!!");
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady(ApplicationReadyEvent event) {
        log.info("Application is Ready!!!");
    }

    @EventListener(ContextStartedEvent.class)
    public void onContextStarted(ContextStartedEvent event) {
        log.info("Context is Started!!!");
    }

    @EventListener(ContextStoppedEvent.class)
    public void onContextStopped(ContextStoppedEvent event) {
        log.info("Context is Stopped!!!");
    }

    @EventListener(ContextClosedEvent.class)
    public void onContextClosed(ContextClosedEvent event) {
        // todo: close/move all of Session before closed
        log.info("Context is Closed!!!");
    }

    @EventListener(ContextRefreshedEvent.class)
    public void onContextRefreshed(ContextRefreshedEvent event) {
        log.info("Context is Refreshed!!!");
    }
}
