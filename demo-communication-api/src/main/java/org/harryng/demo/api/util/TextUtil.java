package org.harryng.demo.api.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.Base64;

public class TextUtil {
    private static final Base64.Encoder b64Encoder = Base64.getEncoder();
    private static final Base64.Decoder b64Decoder = Base64.getDecoder();
    private static final Base64.Encoder b64UrlEncoder = Base64.getUrlEncoder();
    private static final Base64.Decoder b64UrlDecoder = Base64.getUrlDecoder();
    private static final ObjectMapper objectMapper;
    static {
        objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.findAndRegisterModules()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false)
                .configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false)
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//        objectMapper.registerModule(new JavaTimeModule());
    }

    public static String bytesToBase64Url(byte[] arr) {
        return b64UrlEncoder.withoutPadding().encodeToString(arr);
    }

    public static String bytesToBase64(byte[] arr) {
        return b64Encoder.encodeToString(arr);
    }

    public static byte[] base64UrlToBytes(String str) {
        return b64UrlDecoder.decode(str);
    }

    public static byte[] base64ToBytes(String str) {
        return b64Decoder.decode(str);
    }

    public static <T> String objToJson(T obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    public static <T> T jsonToObj(Class<T> clazz, String str) throws JsonProcessingException {
        return objectMapper.readValue(str, clazz);
    }

    public static JsonNode jsonToNode(String str) throws JsonProcessingException {
        return objectMapper.readTree(str);
    }

    public static String noteToJson(JsonNode obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }
}
