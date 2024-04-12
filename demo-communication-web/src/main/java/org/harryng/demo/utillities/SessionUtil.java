package org.harryng.demo.utillities;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import jakarta.servlet.http.HttpServletRequest;
import org.harryng.demo.api.base.dto.SessionHolder;
import org.harryng.demo.api.constant.RequestParam;
import org.harryng.demo.api.constant.SystemKey;

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
import java.util.Base64;
import java.util.UUID;

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

    public static String getJwtToken(SessionHolder sessionHolder, boolean isNew, int sessionDurationInSecond) throws NoSuchAlgorithmException, InvalidKeySpecException {
        final JWTCreator.Builder jwtBuilder = JWT.create();
        if (isNew) {
            final Instant now = Instant.now();
            jwtBuilder.withJWTId(UUID.randomUUID().toString())
                    .withIssuedAt(now)
                    .withNotBefore(now)
                    .withExpiresAt(now.plusSeconds(sessionDurationInSecond));
        }
        final Algorithm algorithm = Algorithm.ECDSA256((ECKey) getPrivateKey());
        jwtBuilder.withIssuer(SystemKey.COMPANY_ID)
                .withSubject(sessionHolder.getUsername())
                .withClaim(RequestParam.HEADER_USER_ID, sessionHolder.getUserId())
                .withClaim(RequestParam.HEADER_SESSION_ID, sessionHolder.getSessionId())
                .sign(algorithm);
        return jwtBuilder.sign(algorithm);
    }

    public static SessionHolder getSessionHolderFromHttpRequest(HttpServletRequest request) throws NoSuchAlgorithmException, InvalidKeySpecException {
        final String hAuth = request.getHeader(RequestParam.HEADER_AUTHORIZATION);
        if (hAuth != null && hAuth.startsWith("Bearer ")) {
            final String jwt = hAuth.substring("Bearer ".length());
            final Algorithm algorithm = Algorithm.ECDSA256((ECKey) getPublicKey());
            final JWTVerifier jwtVerifier = JWT.require(algorithm)
                    .withIssuer(SystemKey.COMPANY_ID)
                    .acceptLeeway(15)
                    .build();
            final DecodedJWT decodedJwt = jwtVerifier.verify(jwt);

            if (decodedJwt.getPayload() != null && !decodedJwt.getClaims().isEmpty()) {
                final String sessionId = decodedJwt.getClaim(RequestParam.HEADER_SESSION_ID).asString();
                final Long userId = decodedJwt.getClaim(RequestParam.HEADER_USER_ID).asLong();
                final String username = decodedJwt.getSubject();
                final Instant before = decodedJwt.getNotBefore() == null ? Instant.now() : decodedJwt.getNotBeforeAsInstant();
                final Instant after = decodedJwt.getExpiresAtAsInstant() == null ? Instant.now() : decodedJwt.getExpiresAtAsInstant();
                return SessionHolder.builder()
                        .sessionId(sessionId)
                        .userId(userId)
                        .username(username)
                        .notBefore(LocalDateTime.from(before))
                        .validity(LocalDateTime.from(after))
                        .build();
            }
        }
        return SessionHolder.ANONYMOUS;
    }
}
