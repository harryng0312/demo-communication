package org.harryng.demo.handler;

import lombok.extern.slf4j.Slf4j;
import org.harryng.demo.api.util.ResponseWrapper;
import org.harryng.demo.api.constant.ResponseCode;
import org.harryng.demo.api.exception.CodedException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
@Slf4j
public class CommunicationExceptionHandler {

    @ExceptionHandler(CodedException.class)
    public ResponseEntity<ResponseWrapper<String>> handleCodedException(final CodedException exception){
        log.error(exception.getMessage(), exception);
        final ResponseWrapper<String> responseWrapper = ResponseWrapper.<String>builder()
                .code(exception.getCode())
                .msg(exception.getMessage())
                .build();
        return ResponseEntity.ok().body(responseWrapper);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ResponseWrapper<String>> handleNoResourceFoundException(final NoResourceFoundException exception){
        log.error(exception.getMessage(), exception);
        final ResponseWrapper<String> responseWrapper = ResponseWrapper.<String>builder()
                .code(ResponseCode.COMMON_ERROR)
                .msg(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(responseWrapper);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseWrapper<String>> handleException(final Exception exception){
        log.error(exception.getMessage(), exception);
        final ResponseWrapper<String> responseWrapper = ResponseWrapper.<String>builder()
                .code(ResponseCode.COMMON_ERROR)
                .msg(exception.getMessage())
                .build();
        return ResponseEntity.badRequest().body(responseWrapper);
    }
}
