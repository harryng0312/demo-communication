package org.harryng.demo.api.asset.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.harryng.demo.api.asset.entity.AssetModel;

import java.util.Objects;

public class AssetValidator implements ConstraintValidator<AssetConstraint, AssetModel> {

    @Override
    public void initialize(AssetConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(AssetModel assetModel, ConstraintValidatorContext constraintValidatorContext) {
        boolean result = true;
        if (assetModel == null) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("asset.name.invalid").addConstraintViolation();
            result = false;
        } else if (assetModel.getName() != null && assetModel.getName().isEmpty()
                || Objects.requireNonNull(assetModel.getName()).contains("[")) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("asset.name.empty").addConstraintViolation();
            result = false;
        }
        return result;
    }
}
