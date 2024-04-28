package org.harryng.demo.api.base.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TreePathValidator implements ConstraintValidator<TreePathValidated, String> {

    @Override
    public void initialize(TreePathValidated constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String assetName, ConstraintValidatorContext constraintValidatorContext) {
        boolean result = true;
//        constraintValidatorContext.disableDefaultConstraintViolation();
        if (assetName == null) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("is null")
                    .addConstraintViolation();
            result = false;
        } else if (assetName.isEmpty() || assetName.contains("[")) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("{asset.name.empty}")
                    .addConstraintViolation();
            result = false;
        }
        return result;
    }
}
