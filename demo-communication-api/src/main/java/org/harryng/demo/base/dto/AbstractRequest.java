package org.harryng.demo.base.dto;

import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

@Data
public abstract class AbstractRequest implements Serializable {
    @NonNull
    private String correlationId = "";
}
