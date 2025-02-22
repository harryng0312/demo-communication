package org.harryng.demo.impl.asset.validator;

import jakarta.annotation.Resource;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.harryng.demo.impl.asset.dto.AssetReq;
import org.harryng.demo.impl.organization.persistence.OrganizationPersistence;

@Slf4j
public class AssetValidator implements ConstraintValidator<AssetDefaultValidated, AssetReq> {

    @Resource
    private OrganizationPersistence organizationPersistence;

    @Override
    public void initialize(AssetDefaultValidated constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(AssetReq asset, ConstraintValidatorContext constraintValidatorContext) {
        boolean result = true;
        constraintValidatorContext.disableDefaultConstraintViolation();
        log.info("===== This is Default validator for asset =====");
        if (asset.getOrgId() == null) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("{asset.orgId.empty}")
                    .addPropertyNode("org")
                    .addPropertyNode("id")
                    .addConstraintViolation();
            result = false;
        } else {
            final var orgExisted = organizationPersistence.existsById(asset.getOrgId());
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
