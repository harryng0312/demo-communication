package org.harryng.demo.impl.base.validator.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.harryng.demo.impl.base.validator.TreePathValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = TreePathValidator.class)
public @interface TreePathValidated {
    String message() default "{treepath.invalid}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
