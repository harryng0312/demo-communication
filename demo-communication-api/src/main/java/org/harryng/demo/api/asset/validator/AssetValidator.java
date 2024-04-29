package org.harryng.demo.api.asset.validator;

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
        constraintValidatorContext.disableDefaultConstraintViolation();
        if (assetModel.getOrgId() == null) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("{asset.orgId.empty}")
                    .addPropertyNode("org")
                    .addPropertyNode("id")
                    .addConstraintViolation();
            result = false;
        } else {
            final var orgExisted = organizationPersistence.existsById(assetModel.getOrgId());
            if (!orgExisted) {
                constraintValidatorContext.buildConstraintViolationWithTemplate("{asset.orgId.invalid}")
                        .addPropertyNode("org")
                        .addPropertyNode("id")
                        .addConstraintViolation();
                result = false;
            }
        }
//        if (!result){
//            constraintValidatorContext.buildConstraintViolationWithTemplate("{asset.invalid}!!!")
//                    .addConstraintViolation();
//        }
        return result;
    }
}
