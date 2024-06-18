package org.harryng.demo.endpoint.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;
import lombok.extern.slf4j.Slf4j;
import org.harryng.demo.api.constant.RequestParams;
import org.harryng.demo.api.util.TextUtil;
import org.harryng.demo.controller.grpc.asset.*;
import org.harryng.demo.endpoint.event.ConversionMessageEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Objects;

@Component
@Slf4j
public class GenericTextChatEventListener {

    @Value("${chat-msg-grpc.server.addr}")
    private String chatServiceGrpcUrl;
    @Value("${chat-msg-grpc.server.port}")
    private int chatServiceGrpcPort;

    @EventListener(value = ConversionMessageEvent.class)
    public void handleSuccessful(ConversionMessageEvent event) {
//        log.info("Handling generic event: {} from source:{}", event.getMessage(), event.getSource());
        // send to gRPC
        ManagedChannel channel = null;
        try {
            if (event.getSource() instanceof WebSocketSession session
                && session.getHandshakeHeaders().getFirst(RequestParams.HEADER_AUTHORIZATION) != null) {
                final String bearerToken = session.getHandshakeHeaders().getFirst(RequestParams.HEADER_AUTHORIZATION);
                if (bearerToken != null && event.getMessage() != null) {
                    final var reqStr = event.getMessage();
                    final long id = TextUtil.jsonToObj(JsonNode.class, reqStr).get("id").asLong();
                    channel = ManagedChannelBuilder.forAddress(chatServiceGrpcUrl, chatServiceGrpcPort)
                            .usePlaintext()
                            .build();
                    final Metadata metadata = new Metadata();
                    final Metadata.Key<String> authKey = Metadata.Key.of(RequestParams.HEADER_AUTHORIZATION, Metadata.ASCII_STRING_MARSHALLER);
                    metadata.put(authKey, bearerToken);
                    final AssetControllerGrpc.AssetControllerBlockingStub stub = AssetControllerGrpc.newBlockingStub(channel)
                            .withInterceptors(MetadataUtils.newAttachHeadersInterceptor(metadata));
                    final AssetIdReq request = AssetIdReq.newBuilder()
                            .setId(id)
                            .build();
                    final AssetResultRes response = stub.findById(request);
//                    log.info("AssetResultRes: {}", response);
                    // response
                    final AssetDtoGrpc assetDtoGrpc = response.getAsset(0);
                    final String resStrBuilder = "{" +
                            "\"id\":" + assetDtoGrpc.getId() + "," +
                            "\"name\":\"" + assetDtoGrpc.getName() + "\"" + "," +
                            "\"description\":\"" + assetDtoGrpc.getDescription() + "\"" + "," +
                            "\"orgId\":" + assetDtoGrpc.getOrgId() +
                            "}";
                    final var assetJson = new TextNode(resStrBuilder);
                    final var msg = new TextMessage(TextUtil.objToJson(assetJson));
                    session.sendMessage(msg);
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            if (channel != null) {
                channel.shutdown();
            }
        }
    }
}
