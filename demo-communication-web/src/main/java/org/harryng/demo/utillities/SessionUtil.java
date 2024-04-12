package org.harryng.demo.utillities;

import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.harryng.demo.api.base.dto.SessionHolder;
import org.harryng.demo.api.constant.RequestParam;
import org.harryng.demo.api.constant.SystemKey;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

public class SessionUtil {

    private static PrivateKey getPrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        final var privKeyStr = "MEECAQAwEwYHKoZIzj0CAQYIKoZIzj0DAQcEJzAlAgEBBCAq2yVQNIrvEwtBuenddC11vI3Vsh5gEjRGmGFlvsbb2w";
        final byte[] privKeyBytes = Base64.getUrlDecoder().decode(privKeyStr);
        final var privKeySpec = new PKCS8EncodedKeySpec(privKeyBytes);
        return KeyFactory.getInstance("EC").generatePrivate(privKeySpec);
    }

    public static PublicKey getPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        final String pubKeyStr = "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEiAe6CTi3t7dtK1z86KIwKZNdWHiakCFGn93em67k1NQjXt_TqupDdlxXfa-OqW8xZZC7BrmUsib-ODZi4BG4-g";
        final byte[] pubKeyBytes = Base64.getUrlDecoder().decode(pubKeyStr);
        final var pubKeySpec = new X509EncodedKeySpec(pubKeyBytes);
        return KeyFactory.getInstance("EC").generatePublic(pubKeySpec);
    }

    public static String getJwtToken(SessionHolder sessionHolder, boolean isNew, int sessionDurationInMillis) throws NoSuchAlgorithmException, InvalidKeySpecException {
        final JwtBuilder builder = Jwts.builder();
        builder.header();
        if(isNew){
            final Instant now = Instant.now();
            builder.id(UUID.randomUUID().toString())
                    .issuedAt(Date.from(now))
                    .notBefore(Date.from(now))
                    .expiration(Date.from(now.plusMillis(sessionDurationInMillis)));
        }
        builder.issuer(SystemKey.COMPANY_ID)
                .subject(sessionHolder.getUsername())
                .claim(RequestParam.HEADER_USER_ID, sessionHolder.getUserId())
                .claim(RequestParam.HEADER_SESSION_ID, sessionHolder.getSessionId())
                .signWith(getPrivateKey());
        return builder.compact();
    }

    public static SessionHolder getSessionHolderFromHttpRequest(HttpServletRequest request) throws NoSuchAlgorithmException, InvalidKeySpecException {
        final String hAuth = request.getHeader(RequestParam.HEADER_AUTHORIZATION);
        if(hAuth !=null && hAuth.startsWith("Bearer ")) {
            final String jwt = hAuth.substring("Bearer ".length());
            final JwtParser jwtParser = Jwts.parser()
                    .requireIssuer(SystemKey.COMPANY_ID)
                    .clockSkewSeconds(15)
                    .verifyWith(getPublicKey())
                    .build();
            final Jws<Claims> claims = jwtParser.parseSignedClaims(jwt);
            if(claims.getPayload()!=null && !claims.getPayload().isEmpty()) {
                final String sessionId = (String) claims.getPayload().getOrDefault(RequestParam.HEADER_SESSION_ID, "");
                final Long userId = Long.valueOf((String) claims.getPayload().getOrDefault(RequestParam.HEADER_USER_ID, "0"));
                final String username = claims.getPayload().getSubject();
                final LocalDateTime before = claims.getPayload().getNotBefore() == null? LocalDateTime.now():
                        claims.getPayload().getNotBefore().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                final LocalDateTime after = claims.getPayload().getExpiration() == null? LocalDateTime.now():
                        claims.getPayload().getExpiration().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                return SessionHolder.builder()
                        .sessionId(sessionId)
                        .userId(userId)
                        .username(username)
                        .notBefore(before)
                        .validity(after)
                        .build();
            }
        }
        return SessionHolder.ANONYMOUS;
    }
}
