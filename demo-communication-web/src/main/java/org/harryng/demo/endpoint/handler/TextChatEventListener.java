package org.harryng.demo.endpoint.handler;

import lombok.extern.slf4j.Slf4j;
import org.harryng.demo.endpoint.event.ConversionMessageEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

//@Component
@Slf4j
public class TextChatEventListener implements ApplicationListener<ConversionMessageEvent> {
    @Override
    public void onApplicationEvent(ConversionMessageEvent event) {
        log.info("Handling TextChatEvent: {}", event);
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
