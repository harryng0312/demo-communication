package org.harryng.demo.user.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.keycloak.OAuthErrorException;
import org.keycloak.adapters.ServerRequest;
import org.keycloak.adapters.installed.KeycloakInstalled;
import org.keycloak.common.VerificationException;
import org.keycloak.representations.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestAuthService {
    static Logger logger = LoggerFactory.getLogger(TestAuthService.class);
    static String keycloakJsonFilePath = "../setup/keycloak.json";
    ExecutorService executorService = null;

    @Before
    public void init(){
        executorService = Executors.newSingleThreadExecutor();
    }

    @After
    public void close(){
        executorService.shutdown();
    }

    @Test
    public void testLogin() {
        logger.info("=====");
        String tokenStr = "";
        // reads the configurat ion from classpath: META-INF/keycloak.json
        InputStream is = null;
        KeycloakInstalled keycloak = null;
        try {
            is = new FileInputStream(keycloakJsonFilePath);
        } catch (FileNotFoundException e) {
            logger.error("", e);
        }
        // opens desktop browser
        try {
            keycloak = new KeycloakInstalled(is);
//            keycloak.loginDesktop();
            keycloak.login();
            AccessToken token = keycloak.getToken();
            // use token to send backend request
            // ensure token is valid for at least 20 seconds
            KeycloakInstalled finalKeycloak = keycloak;
            executorService.submit(() -> {
                logger.info("Logged in...");
                logger.info("Token: " + token.getSubject());
                logger.info("Username: " + token.getPreferredUsername());
                logger.info("AccessToken: " + finalKeycloak.getTokenString());
                int timeoutSeconds = 20;
                System.out.printf("Logging out in...%d Seconds%n", timeoutSeconds);
                try {
                    TimeUnit.SECONDS.sleep(timeoutSeconds);
                } catch (InterruptedException e) {
                    logger.error("", e);
                }

                System.out.println("Exiting...");

            });
        } catch (IOException e) {
            logger.error("", e);
        } catch (VerificationException e) {
            logger.error("", e);
        } catch (OAuthErrorException e) {
            logger.error("", e);
        } catch (URISyntaxException e) {
            logger.error("", e);
        } catch (ServerRequest.HttpFailure e) {
            logger.error("", e);
        } catch (InterruptedException e) {
            logger.error("", e);
        } finally {
            // when you want to logout the user.
            try {
                keycloak.logout();
            } catch (IOException e) {
                logger.error("", e);
            } catch (InterruptedException e) {
                logger.error("", e);
            } catch (URISyntaxException e) {
                logger.error("", e);
            }
        }
        try {
            is.close();
        } catch (IOException ex) {
            logger.error("", ex);
        }
    }
}
