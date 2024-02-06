package org.harryng.demo.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.websocket.DecodeException;
import jakarta.websocket.Decoder;
import jakarta.websocket.EndpointConfig;
import org.harryng.demo.util.TextUtil;

public class ChatMessageDecoder implements Decoder.Text<ChatMessage> {

    @Override
    public ChatMessage decode(String s) throws DecodeException {
        try {
            return TextUtil.jsonToObj(ChatMessage.class, s);
        } catch (JsonProcessingException e) {
            throw new DecodeException(s, e.getMessage(), e);
        }
    }

    @Override
    public boolean willDecode(String s) {
        return (s != null);
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