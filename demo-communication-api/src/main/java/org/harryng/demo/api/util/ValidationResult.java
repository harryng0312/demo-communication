package org.harryng.demo.api.util;

import jakarta.validation.ConstraintViolation;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class ValidationResult<T> implements Serializable {
    @NonNull
    @Singular
//    @Builder.Default
    private List<ValidationError> validationErrors;
    @NonNull
    @Builder.Default
    private T value = null;

    public boolean isValid() {
        return validationErrors.isEmpty();
    }

    public static <T> List<ValidationError> toValidationErrors(@NonNull Set<ConstraintViolation<T>> constraintViolations) {
        final List<ValidationError> errors = new ArrayList<>();
        for (ConstraintViolation<T> constraintViolation : constraintViolations) {
            errors.add(ValidationError.of(
                    constraintViolation.getPropertyPath().toString(),
                    constraintViolation.getMessage(),
                    constraintViolation.getInvalidValue()));
        }
        return errors;
    }
}
