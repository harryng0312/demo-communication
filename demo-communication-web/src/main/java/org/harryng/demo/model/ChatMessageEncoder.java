package org.harryng.demo.model;

import com.google.gson.Gson;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;
import jakarta.websocket.EndpointConfig;

public class ChatMessageEncoder implements Encoder.Text<ChatMessage> {

    private static final Gson gson = new Gson();

    @Override
    public String encode(ChatMessage message) throws EncodeException {
        return gson.toJson(message);
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
