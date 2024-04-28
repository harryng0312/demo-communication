package org.harryng.demo.api.asset.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AssetNameValidator implements ConstraintValidator<AssetNameNotEmpty, String> {

    @Override
    public void initialize(AssetNameNotEmpty constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String assetName, ConstraintValidatorContext constraintValidatorContext) {
        boolean result = true;
        if (assetName == null) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("asset.name.invalid").addConstraintViolation();
            result = false;
        } else if (assetName.isEmpty() || assetName.contains("[")) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("asset.name.empty").addConstraintViolation();
            result = false;
        }
        return result;
    }
}
