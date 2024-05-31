package org.harryng.demo.user.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.extern.slf4j.Slf4j;
import org.harryng.demo.Application;
import org.harryng.demo.api.constant.RequestParams;
import org.harryng.demo.api.constant.SystemKey;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.security.*;
import java.security.interfaces.ECKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.util.Base64;
import java.util.UUID;

//@SpringBootTest(classes = Application.class)
@Import(Application.class)
@Slf4j
public class TestJwt {
    @Test
    public void createKeyPair() throws NoSuchAlgorithmException {
//        final var keyPair = Jwts.SIG.ES256.keyPair().build();
        final KeyPairGenerator generator = KeyPairGenerator.getInstance("EC");
        generator.initialize(256, new SecureRandom());
        final KeyPair keyPair = generator.generateKeyPair();
        final var privKey = keyPair.getPrivate();
        final var pubKey = keyPair.getPublic();
        final var privKeyStr = Base64.getEncoder().encodeToString(privKey.getEncoded());
        final var pubKeyStr = Base64.getEncoder().encodeToString(pubKey.getEncoded());
        log.info("Private Key:{}", privKeyStr);
        log.info("Public Key:{}", pubKeyStr);
    }

    @Test
    public void createJwt() throws NoSuchAlgorithmException, InvalidKeySpecException {
        final var durationInSecond = 10_000;
        final var id = UUID.randomUUID().toString();
        final var now = Instant.now();
        final var expiration = now.plusSeconds(durationInSecond);

        final var privKeyStr = "MEECAQAwEwYHKoZIzj0CAQYIKoZIzj0DAQcEJzAlAgEBBCDyy9UoWTvMAySdQJ4otstKPXeYorPLBbeT5RUu/v7pag==";
        final byte[] privKeyBytes = Base64.getDecoder().decode(privKeyStr);
        final var privKeySpec = new PKCS8EncodedKeySpec(privKeyBytes);
        final ECKey privKey = (ECKey) KeyFactory.getInstance("EC").generatePrivate(privKeySpec);
//        final var pubKeyStr = "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEvTfMEjJnAUgGV2/d+cnNf0NjXQOX7MFc/q6OXKm1K4M8teqvQF07kqDxaRenMv5zAEwmUqM9ikgG6D3kBTlvAw==";
//        final byte[] pubKeyBytes = Base64.getDecoder().decode(pubKeyStr);
//        final var pubKeySpec = new X509EncodedKeySpec(pubKeyBytes);
//        final ECPublicKey pubKey = (ECPublicKey) KeyFactory.getInstance("EC").generatePublic(pubKeySpec);

//        final String jwt = Jwts.builder()
//                .header()
//                .and()
//                .issuer("demo-comm")
//                .issuedAt(nowDate)
//                .subject("username")
//                .notBefore(nowDate)
//                .id(id)
//                .expiration(expiration)
//                .signWith(privKey)
//                .compact();

        final String sessionId = UUID.randomUUID().toString();
        final Algorithm algorithm = Algorithm.ECDSA256(privKey);
        final String jwt = JWT.create()
                .withJWTId(id)
                .withIssuer(SystemKey.COMPANY_ID)
                .withIssuedAt(now)
                .withSubject("username 01")
                .withNotBefore(now)
                .withExpiresAt(expiration)
                .withClaim(RequestParams.HEADER_SESSION_ID, sessionId)
                .withClaim(RequestParams.HEADER_USER_ID, 1L)
                .withClaim(RequestParams.PARAM_LOCALE, "en")
                .sign(algorithm);
        log.info("JWT:{}", jwt);
    }

    @Test
    public void parseJwt() throws NoSuchAlgorithmException, InvalidKeySpecException {
        final int skewSeconds = 10;
        final var jwt = "eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiI5NTAzODE4OS0yMWZhLTQ5MWEtOWI3Mi1mN2JlYjM3NjQ4MmYiLCJpc3MiOiJkZW1vLWNvbW0iLCJpYXQiOjE3MTI5NDA3OTcsInN1YiI6InVzZXJuYW1lIiwibmJmIjoxNzEyOTQwNzk3LCJleHAiOjE3MTI5NTA3OTd9.JLIJv40l7hj9AzptrjTMM5585Dmil98KtpH9KuWnyerDSlmDpVYdFwzeW8-vcMMW8aA6dNIr8QYhGzz3RT3_Cg";
        final var pubKeyStr = "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEvTfMEjJnAUgGV2/d+cnNf0NjXQOX7MFc/q6OXKm1K4M8teqvQF07kqDxaRenMv5zAEwmUqM9ikgG6D3kBTlvAw==";
        final byte[] pubKeyBytes = Base64.getDecoder().decode(pubKeyStr);
        final var pubKeySpec = new X509EncodedKeySpec(pubKeyBytes);
        final ECKey pubKey = (ECKey) KeyFactory.getInstance("EC").generatePublic(pubKeySpec);

//        final var jwtParser = Jwts.parser()
//                .requireIssuer("demo-comm")
//                .verifyWith(pubKey)
//                .build();
////        jwtParser.parse();
//        final Jws<Claims> jwtInfo = jwtParser.parseSignedClaims(jwt);
//        log.info("Jwt data:{}", jwtInfo);

        final Algorithm algorithm = Algorithm.ECDSA256(pubKey);
        final JWTVerifier verifier = JWT.require(algorithm)
                .acceptLeeway(skewSeconds)
                .withIssuer(SystemKey.COMPANY_ID)
                .build();
        final DecodedJWT decodedJwt = verifier.verify(jwt);
        log.info("DecodedJWT:{}.{}.{}",
                new String(Base64.getDecoder().decode(decodedJwt.getHeader())),
                new String(Base64.getDecoder().decode(decodedJwt.getPayload())),
                decodedJwt.getSignature());
    }
}
