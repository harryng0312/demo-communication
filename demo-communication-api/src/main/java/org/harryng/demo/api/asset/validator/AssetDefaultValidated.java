package org.harryng.demo.api.asset.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AssetValidator.class)
@NotNull
public @interface AssetDefaultValidated {
    String message() default "{asset.invalid}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
