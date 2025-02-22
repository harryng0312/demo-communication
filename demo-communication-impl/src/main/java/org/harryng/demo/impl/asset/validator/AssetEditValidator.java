package org.harryng.demo.impl.asset.validator;

import jakarta.annotation.Resource;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.harryng.demo.impl.asset.dto.AssetReq;
import org.harryng.demo.impl.organization.persistence.OrganizationPersistence;

@Slf4j
public class AssetEditValidator implements ConstraintValidator<AssetEditValidated, AssetReq> {

    @Resource
    private OrganizationPersistence organizationPersistence;

    @Override
    public void initialize(AssetEditValidated constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(AssetReq asset, ConstraintValidatorContext constraintValidatorContext) {
        boolean result = true;
        constraintValidatorContext.disableDefaultConstraintViolation();
        log.info("===== This is Edit validator for asset =====");
        if (asset.getId() == null) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("{id.invalid}")
                    .addPropertyNode("id")
                    .addConstraintViolation();
            result = false;
        } else if (asset.getOrgId() == null) {
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
