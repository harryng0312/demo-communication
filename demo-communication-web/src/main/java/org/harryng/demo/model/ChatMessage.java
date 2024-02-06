package org.harryng.demo.model;

import lombok.Data;

@Data
public class ChatMessage {
    private String from;
    private String to;
    private String content;
}
