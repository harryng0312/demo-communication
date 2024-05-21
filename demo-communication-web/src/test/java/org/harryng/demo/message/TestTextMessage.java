package org.harryng.demo.message;

import jakarta.websocket.*;
import lombok.extern.slf4j.Slf4j;
import org.harryng.demo.Application;
import org.harryng.demo.ws.TestWebSocketClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.wildfly.common.Assert;

import java.io.IOException;
import java.net.URI;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(Application.class)
@ClientEndpoint
@Slf4j
public class TestTextMessage {
    @LocalServerPort
    private int randomServerPort;
    private WebSocketContainer container;
    private ClientEndpointConfig clientConfig;
//    private Session session;

    @BeforeEach
    public void setup() {
        container = ContainerProvider.getWebSocketContainer();
        final var token = "eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiI1NjhjNWEwMy0wNTI0LTQyMTUtYWU1Yy1lNTZlY2E5Y2JmYmIiLCJpc3MiOiJMb2NhbCBIb21lIiwiaWF0IjoxNzE2MzExMDg1LCJzdWIiOiJ1c2VybmFtZSAwMSIsIm5iZiI6MTcxNjMxMTA4NSwiZXhwIjoxNzE2MzIxMDg1LCJzZXNzaW9uX2lkIjoiMWYyNmU5NjctNjg3MC00ZmIwLThhZGItYzZhMWViMTliYTFlIiwidXNlcl9pZCI6MSwibG9jYWxlIjoiZW4ifQ.Q0OFO_ECYASJikXHlP8fd309er-ZTLMBRhPZ72Wg32I5GdDnJBr8n9lCgO0LPd_SVGMSTnz-q6v_FN8YCvXTrg";
        final ClientEndpointConfig.Configurator configurator = new ClientEndpointConfig.Configurator() {
            @Override
            public void beforeRequest(Map<String, List<String>> headers) {
                headers.put("Authorization", Collections.singletonList("Bearer " + token));
            }
        };
        // Create ClientEndpointConfig
        clientConfig = ClientEndpointConfig.Builder.create()
                .configurator(configurator)
                .build();
//        container.setDefaultMaxTextMessageBufferSize(100);
        container.setDefaultMaxTextMessageBufferSize(256 * 1024);
    }

    @Test
    public void testTextChatHandler() throws DeploymentException, IOException, InterruptedException {
        final var wsUrl = MessageFormat.format("ws://localhost:{0,number,#}/web/ws/chat-handler", randomServerPort);
        log.info("wsUrl: {}", wsUrl);
        final var message = "{\n" +
                "    \"id\": \"0b460b11-44d5-47d5-b7be-2d02b5dcf0d5\",\n" +
                "    \"senderId\": \"1\",\n" +
                "    \"recipientId\": \"2\",\n" +
                "    \"type\": 1,\n" +
                "    \"recipientType\": 1,\n" +
                "\n" +
                "    \"sendTime\": 1,\n" +
                "    \"receivedTime\": 1,\n" +
                "    \"status\": 1,\n" +
                "    \"replacedId\": \"\",\n" +
                "    \n" +
                "    \"content\": \"Đây là nội dung gửi từ client lên Server\"\n" +
                "}";
        log.info("===== Connecting =====");
        final var uri = URI.create(wsUrl);
        try (final var session = this.container.connectToServer(TestWebSocketClient.class, clientConfig, uri)) {
            while (!session.isOpen()) {
                // sometime it is doesn't work, but I dont know solution of this problem
                // wait until socket is open
                log.info("waiting for connection");
                Thread.sleep(2_000L);
            }
            Assert.assertTrue(session.isOpen());
            log.info("===== Connected =====");
            session.getBasicRemote().sendText(message);
            Thread.sleep(2_000L);
        } finally {
            log.info("===== Disconnected =====");
        }
    }
}
