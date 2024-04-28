package org.harryng.demo.api.asset.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotEmpty;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@NotEmpty
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = AssetNameValidator.class)
public @interface AssetNameNotEmpty {
    String message() default "{asset.name.invalid}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
