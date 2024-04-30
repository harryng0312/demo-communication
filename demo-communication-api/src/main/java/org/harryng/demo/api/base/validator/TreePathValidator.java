package org.harryng.demo.api.base.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.harryng.demo.api.base.validator.annotation.TreePathValidated;

import java.util.regex.Pattern;

@Slf4j
public class TreePathValidator implements ConstraintValidator<TreePathValidated, String> {

    final Pattern pattern;

    public TreePathValidator() {
        this.pattern = Pattern.compile("^(/[a-zA-Z0-9]+)+$");
    }

    @Override
    public void initialize(TreePathValidated constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String treepath, ConstraintValidatorContext constraintValidatorContext) {
        boolean result = true;
        constraintValidatorContext.disableDefaultConstraintViolation();
        log.info("===== This is Treepath validator =====");
        if (treepath == null || treepath.isEmpty()) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("{treepath.invalid}")
                    .addConstraintViolation();
            result = false;
        } else {
            treepath = treepath.trim();
            if (!pattern.matcher(treepath).matches()) {
                constraintValidatorContext.buildConstraintViolationWithTemplate("{treepath.invalid}")
                        .addConstraintViolation();
                result = false;
            }
        }
        return result;
    }
}
