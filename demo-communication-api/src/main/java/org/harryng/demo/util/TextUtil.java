package org.harryng.demo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Base64;

public class TextUtil {
    private static final Base64.Encoder b64Encoder = Base64.getEncoder();
    private static final Base64.Decoder b64Decoder = Base64.getDecoder();
    private static final Base64.Encoder b64UrlEncoder = Base64.getUrlEncoder();
    private static final Base64.Decoder b64UrlDecoder = Base64.getUrlDecoder();
    private static final ObjectMapper objectMapper;
    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false)
                .configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
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
}
