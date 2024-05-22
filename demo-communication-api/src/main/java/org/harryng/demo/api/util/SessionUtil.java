package org.harryng.demo.api.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import jakarta.servlet.http.HttpServletRequest;
import org.harryng.demo.api.constant.RequestParams;
import org.harryng.demo.api.constant.SystemKey;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketSession;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.ECKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Locale;

public class SessionUtil {

    private static PrivateKey getPrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        final var privKeyStr = "MEECAQAwEwYHKoZIzj0CAQYIKoZIzj0DAQcEJzAlAgEBBCDyy9UoWTvMAySdQJ4otstKPXeYorPLBbeT5RUu/v7pag==";
        final byte[] privKeyBytes = Base64.getDecoder().decode(privKeyStr);
        final var privKeySpec = new PKCS8EncodedKeySpec(privKeyBytes);
        return KeyFactory.getInstance("EC").generatePrivate(privKeySpec);
    }

    public static PublicKey getPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        final String pubKeyStr = "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEvTfMEjJnAUgGV2/d+cnNf0NjXQOX7MFc/q6OXKm1K4M8teqvQF07kqDxaRenMv5zAEwmUqM9ikgG6D3kBTlvAw==";
        final byte[] pubKeyBytes = Base64.getDecoder().decode(pubKeyStr);
        final var pubKeySpec = new X509EncodedKeySpec(pubKeyBytes);
        return KeyFactory.getInstance("EC").generatePublic(pubKeySpec);
    }

    public static String createJwtToken(SessionHolder sessionHolder, boolean isNew, int sessionDurationInSecond, String jwtId) throws NoSuchAlgorithmException, InvalidKeySpecException {
        final JWTCreator.Builder jwtBuilder = JWT.create();
        if (isNew) {
            final Instant now = Instant.now();
            jwtBuilder.withJWTId(jwtId)
                    .withIssuedAt(now)
                    .withNotBefore(now)
                    .withExpiresAt(now.plusSeconds(sessionDurationInSecond));
        }
        final Algorithm algorithm = Algorithm.ECDSA256((ECKey) getPrivateKey());
        jwtBuilder.withIssuer(SystemKey.COMPANY_ID)
                .withSubject(sessionHolder.getUsername())
                .withClaim(RequestParams.HEADER_USER_ID, sessionHolder.getUserId())
                .withClaim(RequestParams.HEADER_SESSION_ID, sessionHolder.getSessionId())
                .withClaim(RequestParams.PARAM_LOCALE, sessionHolder.getLocale().getDisplayName());
        return jwtBuilder.sign(algorithm);
    }

    public static SessionHolder getSessionHolderFromAccessToken(String jwt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (!jwt.isBlank()) {
            final Algorithm algorithm = Algorithm.ECDSA256((ECKey) getPublicKey());
            final JWTVerifier jwtVerifier = JWT.require(algorithm)
                    .withIssuer(SystemKey.COMPANY_ID)
                    .acceptLeeway(15) // Not Before, Issued At and Expires At
//                    .acceptExpiresAt(15)
                    .build();
            final DecodedJWT decodedJwt = jwtVerifier.verify(jwt);

            if (decodedJwt.getPayload() != null && !decodedJwt.getClaims().isEmpty()) {
                final String sessionId = decodedJwt.getClaim(RequestParams.HEADER_SESSION_ID).asString();
                final Long userId = decodedJwt.getClaim(RequestParams.HEADER_USER_ID).asLong();
                final String localeStr = decodedJwt.getClaim(RequestParams.PARAM_LOCALE).asString();
                final Locale locale;
                if(localeStr!=null && !localeStr.isBlank()){
                    locale = Locale.forLanguageTag(localeStr);
                }else{
                    locale = Locale.ENGLISH;
                }
                final String username = decodedJwt.getSubject();
                final Instant before = decodedJwt.getNotBeforeAsInstant() == null ? Instant.now() : decodedJwt.getNotBeforeAsInstant();
                final Instant after = decodedJwt.getExpiresAtAsInstant() == null ? Instant.now() : decodedJwt.getExpiresAtAsInstant();
                return SessionHolder.builder()
                        .sessionId(sessionId)
                        .userId(userId)
                        .username(username)
                        .locale(locale)
                        .notBefore(LocalDateTime.ofInstant(before, ZoneId.systemDefault()))
                        .validity(LocalDateTime.ofInstant(after, ZoneId.systemDefault()))
                        .build();
            }
        }
        return SessionHolder.ANONYMOUS;
    }

    public static String getToken(ServerHttpRequest request) {
        final String headerAuth = request.getHeaders().getFirst(RequestParams.HEADER_AUTHORIZATION);
        final String paramAccessToken = request.getHeaders().getFirst(RequestParams.PARAM_ACCESS_TOKEN);
        final String jwt;
        if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
            jwt = headerAuth.substring(7);
        } else if (paramAccessToken != null && !paramAccessToken.isBlank()) {
            jwt = paramAccessToken;
        } else {
            jwt = "";
        }
        return jwt;
    }

    public static String getToken(HttpServletRequest httpServletRequest) {
        final String headerAuth = httpServletRequest.getHeader(RequestParams.HEADER_AUTHORIZATION);
        final String paramAccessToken = httpServletRequest.getParameter(RequestParams.PARAM_ACCESS_TOKEN);
        final String jwt;
        if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
            jwt = headerAuth.substring(7);
        } else if (paramAccessToken != null && !paramAccessToken.isBlank()) {
            jwt = paramAccessToken;
        } else {
            jwt = "";
        }
        return jwt;
    }

    public static String getToken(HttpHeaders httpHeaders) {
        final String headerAuth = httpHeaders.getFirst(RequestParams.HEADER_AUTHORIZATION);
        final String paramAccessToken = httpHeaders.getFirst(RequestParams.PARAM_ACCESS_TOKEN);
        final String jwt;
        if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
            jwt = headerAuth.substring(7);
        } else if (paramAccessToken != null && !paramAccessToken.isBlank()) {
            jwt = paramAccessToken;
        } else {
            jwt = "";
        }
        return jwt;
    }

    public static boolean isAnonymous(SessionHolder sessionHolder) {
        if(sessionHolder == null){
            return true;
        }
        return SessionHolder.ANONYMOUS.getUserId().equals(sessionHolder.getUserId());
    }

    public static SessionHolder getSessionHolder(WebSocketSession session) throws NoSuchAlgorithmException, InvalidKeySpecException {
        final var token = getToken(session.getHandshakeHeaders());
        return getSessionHolderFromAccessToken(token);
    }
}
