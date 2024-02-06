package org.harryng.demo.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;
import jakarta.websocket.EndpointConfig;
import org.harryng.demo.util.TextUtil;

public class ChatMessageEncoder implements Encoder.Text<ChatMessage> {

    @Override
    public String encode(ChatMessage message) throws EncodeException {
        try {
            return TextUtil.objToJson(message);
        } catch (JsonProcessingException e) {
            throw new EncodeException(message, e.getMessage(), e);
        }
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        // Custom initialization logic
    }

    @Override
    public void destroy() {
        // Close resources
    }
}
