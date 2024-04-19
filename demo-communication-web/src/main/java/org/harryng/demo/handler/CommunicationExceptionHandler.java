package org.harryng.demo.handler;

import lombok.extern.slf4j.Slf4j;
import org.harryng.demo.api.base.dto.ResponseWrapper;
import org.harryng.demo.api.constant.ResponseCode;
import org.harryng.demo.api.exception.CodedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
public class CommunicationExceptionHandler {

    @ExceptionHandler(CodedException.class)
    public ResponseEntity<ResponseWrapper<String>> handleCodedException(final CodedException exception){
        log.error(exception.getMessage(), exception);
        final ResponseWrapper<String> responseWrapper = ResponseWrapper.<String>builder()
                .code(exception.getCode())
                .msg(exception.getMessage())
                .build();
        return ResponseEntity.badRequest().body(responseWrapper);
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
