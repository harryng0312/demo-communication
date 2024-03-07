package org.harryng.demo.base.pojo.dto;

import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

@Data
public abstract class ResponseWrapper<Tbody> implements Serializable {
    @NonNull
    private String correlationId = "";
    @NonNull
    private String code = "";
    @NonNull
    private String msg = "";

    private Tbody data;
}
