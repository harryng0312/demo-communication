package org.harryng.demo.ws;

import jakarta.websocket.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@ClientEndpoint
@Getter
@NoArgsConstructor
@Slf4j
public class TestWebSocketClient extends Endpoint {
    private Session session;

//    @OnOpen
//    public void onOpen(final Session session){
//        log.info("Opening WebSocket connection");
//        this.session = session;
//    }

    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        log.info("Opening WebSocket connection");
        this.session = session;
        this.session.addMessageHandler(String.class, (partialMsg, last) -> {
            log.info("Received a partial message: {} is last: {}", partialMsg, last);
        });
//        this.session.addMessageHandler(String.class, (wholeMsg) -> {
//            log.info("Received a whole message: {}", wholeMsg);
//        });
    }

    @OnClose
    @Override
    public void onClose(Session userSession, CloseReason reason) {
        log.info("Closing WebSocket connection");
        this.session = null;
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("Received message:{}", message);
    }

    @Override
    public void onError(Session session, Throwable throwable) {
        log.error("Error", throwable);
    }

    public void sendMessage(String message) throws IOException {
//        this.session.getAsyncRemote().sendText(message);
        this.session.getBasicRemote().sendText(message);
    }
}
