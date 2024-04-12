package org.harryng.demo.api.base.dto;

import lombok.*;
import org.harryng.demo.api.constant.ResponseCode;

import java.io.Serializable;

@Data
@Builder
public class ResponseWrapper<Tbody> implements Serializable {
    @NonNull @Builder.Default
    private final String correlationId = "";
    @Builder.Default
    private final int code = ResponseCode.SUCCESS;
    @NonNull @Builder.Default
    private final String msg = "";

    private Tbody data;
}
