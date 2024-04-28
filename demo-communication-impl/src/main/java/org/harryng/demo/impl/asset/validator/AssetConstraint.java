package org.harryng.demo.impl.asset.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AssetValidator.class)
@NotNull
public @interface AssetConstraint {
    String message() default "{asset.invalid}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
