package org.harryng.demo.api.util;

import jakarta.validation.ConstraintViolation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationResult<T> implements Serializable {
    private T value;
//    @Builder.Default
    private final List<ValidationError> validationErrors = new ArrayList<>();

    public boolean isValid() {
        return validationErrors.isEmpty();
    }

    public void addValidationErrors(@NonNull Set<ConstraintViolation<T>> constraintViolations) {
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            getValidationErrors().add(ValidationError.of(
                    constraintViolation.getPropertyPath().toString(),
                    constraintViolation.getMessage(),
                    constraintViolation.getInvalidValue()));
        }
    }
}
