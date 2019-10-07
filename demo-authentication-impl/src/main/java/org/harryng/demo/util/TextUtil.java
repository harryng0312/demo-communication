package org.harryng.demo.util;

import java.util.Base64;

public class TextUtil {
    private static Base64.Encoder b64Encoder = Base64.getEncoder();
    private static Base64.Decoder b64Decoder = Base64.getDecoder();
    private static Base64.Encoder b64UrlEncoder = Base64.getUrlEncoder();
    private static Base64.Decoder b64UrlDecoder = Base64.getUrlDecoder();

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
}
