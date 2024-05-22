package org.harryng.demo.endpoint.handler;

import lombok.extern.slf4j.Slf4j;
import org.harryng.demo.endpoint.event.ConversionMessageEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GenericTextChatEventListener {

    @EventListener(value = ConversionMessageEvent.class)
    public void handleSuccessful(ConversionMessageEvent event) {
        log.info("Handling generic event: {}", event);
    }
}
