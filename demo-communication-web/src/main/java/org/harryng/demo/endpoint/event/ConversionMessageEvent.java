package org.harryng.demo.endpoint.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class ConversionMessageEvent extends ApplicationEvent {

    private final String message;

    public ConversionMessageEvent(Object source, Clock clock, String message) {
        super(source, clock);
        this.message = message;
    }

    public ConversionMessageEvent(Object source, String message) {
        super(source);
        this.message = message;
    }
}
