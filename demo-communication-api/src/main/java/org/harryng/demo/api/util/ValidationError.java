package org.harryng.demo.api.util;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

@Data
@Builder
public class ValidationError implements Serializable {
    @NonNull @Builder.Default
    private Long id = 0L;
    @NonNull @Builder.Default
    private String field = "";
    @NonNull @Builder.Default
    private String message = "";
    @NonNull @Builder.Default
    private Object rejectedValue = "";

    public static ValidationError of(@NonNull String field, @NonNull String message, @NonNull Object rejectedValue) {
        return ValidationError.builder().field(field).message(message).rejectedValue(rejectedValue).build();
    }
}
