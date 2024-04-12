package org.harryng.demo.user.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.harryng.demo.main.Application;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@SpringBootTest(classes = Application.class)
@Import(Application.class)
@Slf4j
public class TestJwt {
    @Test
    public void createKeyPair() {
        final var keyPair = Jwts.SIG.ES256.keyPair().build();
        final var privKey = keyPair.getPrivate();
        final var pubKey = keyPair.getPublic();
        final var privKeyStr = Base64.getUrlEncoder().withoutPadding().encodeToString(privKey.getEncoded());
        final var pubKeyStr = Base64.getUrlEncoder().withoutPadding().encodeToString(pubKey.getEncoded());
        System.out.printf("Private Key:%s\n", privKeyStr);
        System.out.printf("Public Key:%s\n", pubKeyStr);
    }
    @Test
    public void createJwt() throws NoSuchAlgorithmException, InvalidKeySpecException {
        final var durationInSecond = 1000;
        final var id = UUID.randomUUID().toString();
        final var now = LocalDateTime.now();
        final var nowDate = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        final var expiration = Date.from(now.plusSeconds(durationInSecond).atZone(ZoneId.systemDefault()).toInstant());

        final var privKeyStr = "MEECAQAwEwYHKoZIzj0CAQYIKoZIzj0DAQcEJzAlAgEBBCAq2yVQNIrvEwtBuenddC11vI3Vsh5gEjRGmGFlvsbb2w";
        System.out.printf("Private Key:%s\n", privKeyStr);

        final byte[] privKeyBytes = Base64.getUrlDecoder().decode(privKeyStr);
        final var privKeySpec = new PKCS8EncodedKeySpec(privKeyBytes);
        final var privKey = KeyFactory.getInstance("EC").generatePrivate(privKeySpec);

        final String jwt = Jwts.builder()
                .header()
                .and()
                .issuer("demo-comm")
                .issuedAt(nowDate)
                .subject("username")
                .notBefore(nowDate)
                .id(id)
                .expiration(expiration)
                .signWith(privKey)
                .compact();
        System.out.printf("JWT:%s\n", jwt);

    }

    @Test
    public void parseJwt() throws NoSuchAlgorithmException, InvalidKeySpecException {
        final var jwt = "eyJhbGciOiJFUzI1NiJ9.eyJpc3MiOiJkZW1vLWNvbW0iLCJpYXQiOjE3MTI4OTM3NjMsInN1YiI6InVzZXJuYW1lIiwibmJmIjoxNzEyODkzNzYzLCJqdGkiOiJhYmJkYTkzNC04YWNhLTQ2ODItODhmOS1mMmUwZWQ4MDE2MmUiLCJleHAiOjE3MTI4OTQ3NjN9.bCwhwXSqldDGvEvhFy5nnQhHqWvsizipN01o5W9M6KBVRJvADorJDq8DVGy4UnROkp57UPVk0kwv0mOsSL_zQQ";
        System.out.printf("JWT:%s\n", jwt);
        final String pubKeyStr = "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEiAe6CTi3t7dtK1z86KIwKZNdWHiakCFGn93em67k1NQjXt_TqupDdlxXfa-OqW8xZZC7BrmUsib-ODZi4BG4-g";
        final byte[] pubKeyBytes = Base64.getUrlDecoder().decode(pubKeyStr);
        final var pubKeySpec = new X509EncodedKeySpec(pubKeyBytes);
        final var pubKey = KeyFactory.getInstance("EC").generatePublic(pubKeySpec);

        final var jwtParser = Jwts.parser()
                .requireIssuer("demo-comm")
                .verifyWith(pubKey)
                .build();
//        jwtParser.parse();
        final Jws<Claims> jwtInfo = jwtParser.parseSignedClaims(jwt);
        System.out.printf("Jwt data:%s\n", jwtInfo);

    }
}
