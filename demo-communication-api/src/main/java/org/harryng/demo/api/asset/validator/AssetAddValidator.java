package org.harryng.demo.api.asset.validator;

import jakarta.annotation.Resource;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.harryng.demo.api.asset.dto.AssetDto;
import org.harryng.demo.api.asset.entity.AssetModel;
import org.harryng.demo.api.asset.validator.annotation.AssetAddValidated;
import org.harryng.demo.api.organization.persistence.OrganizationPersistence;

@Slf4j
public class AssetAddValidator implements ConstraintValidator<AssetAddValidated, AssetDto> {

    @Resource
    private OrganizationPersistence organizationPersistence;

    @Override
    public void initialize(AssetAddValidated constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(AssetDto asset, ConstraintValidatorContext constraintValidatorContext) {
        boolean result = true;
        constraintValidatorContext.disableDefaultConstraintViolation();
        log.info("===== This is Add validator for asset =====");
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
