package org.harryng.demo.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
public class CommunicationExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(final Exception exception){
        log.error(exception.getMessage(), exception);
        return ResponseEntity.badRequest().body("Bad Request!");
    }
}
