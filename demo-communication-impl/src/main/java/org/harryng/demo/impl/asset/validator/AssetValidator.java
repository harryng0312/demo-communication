package org.harryng.demo.impl.asset.validator;

import jakarta.annotation.Resource;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.harryng.demo.api.asset.entity.AssetModel;
import org.harryng.demo.api.organization.persistence.OrganizationPersistence;

public class AssetValidator implements ConstraintValidator<AssetConstraint, AssetModel> {

    @Resource
    private OrganizationPersistence organizationPersistence;

    @Override
    public void initialize(AssetConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(AssetModel assetModel, ConstraintValidatorContext constraintValidatorContext) {
        boolean result = true;
        if (assetModel.getOrgId() == null) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("asset.orgId.empty")
                    .addConstraintViolation();
            result = false;
        } else {
            final var orgExisted = organizationPersistence.existsById(assetModel.getOrgId());
            if (!orgExisted) {
                constraintValidatorContext
                        .buildConstraintViolationWithTemplate("asset.orgId.invalid")
                        .addConstraintViolation();
                result = false;
            }
        }
        return result;
    }
}
