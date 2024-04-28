package org.harryng.demo.api.asset.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AssetValidator.class)
public @interface AssetConstraint {
    String message() default "{asset.name.invalid}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
