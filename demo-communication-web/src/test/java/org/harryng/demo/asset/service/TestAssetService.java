package org.harryng.demo.asset.service;

import jakarta.annotation.Resource;
import jakarta.validation.*;
import jakarta.validation.executable.ExecutableValidator;
import lombok.extern.slf4j.Slf4j;
import org.harryng.demo.Application;
import org.harryng.demo.impl.asset.dto.AssetDto;
import org.harryng.demo.impl.asset.entity.AssetImpl;
import org.harryng.demo.impl.asset.service.AssetService;
import org.harryng.demo.impl.base.validator.group.AddValGroup;
import org.harryng.demo.impl.base.validator.group.DefaultValGroup;
import org.harryng.demo.api.util.SessionHolder;
import org.harryng.demo.api.util.ValidationResult;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Set;

@SpringBootTest(classes = Application.class)
@Import(Application.class)
@Slf4j
public class TestAssetService {

    @Resource
    private Validator validator;
    @Resource
    private AssetService assetService;

    @Test
    public void testFieldAndClassValidatorBasic() {
        log.info("test validator basic");
        // Create a ValidatorFactory
        final MessageInterpolator interpolator = new ResourceBundleMessageInterpolator(
                new PlatformResourceBundleLocator("message"));
//        final MessageInterpolator messageInterpolator = new ResourceBundleMessageInterpolator();
        final Configuration<?> configuration = Validation.byDefaultProvider().configure();
        configuration.messageInterpolator(interpolator);

        try (ValidatorFactory factory = configuration.buildValidatorFactory()) {
            // Set up a custom message interpolator with message bundles
            // Use the custom message interpolator when creating the validator
            final Validator validator = factory.getValidator();
            final var now = LocalDateTime.now();
            final AssetImpl asset = new AssetImpl();
            asset.setId(0L);
            asset.setCreatedDate(now);
            asset.setModifiedDate(now);
            asset.setOrgId(0L); // pass the field validator
            asset.setOrgTreepath("/");
            asset.setDescription("");
            asset.setStatus(1);
            asset.setName("[assetname]");

            final ExecutableValidator executableValidator = factory.getValidator().forExecutables();
            log.info("executableValidator:{}", executableValidator);
            final Set<ConstraintViolation<AssetImpl>> violations = validator.validate(asset);
            log.info("validation result:{}", violations.size());
            log.info("validation detail:{}", violations);
        }
    }

    @Test
    public void testFieldAndClassValidator() {
        log.info("test validator spring");
        // Create a ValidatorFactory
        // Set up a custom message interpolator with message bundles
        // Use the custom message interpolator when creating the validator
        final var now = LocalDateTime.now();
        final AssetImpl asset = new AssetImpl();
        asset.setId(0L);
        asset.setCreatedDate(now);
        asset.setModifiedDate(now);
        asset.setOrgId(0L); // pass the field validator
        asset.setOrgTreepath("/123/345");
        asset.setDescription("");
        asset.setStatus(1);
        asset.setName("[assetname]");

        final Set<ConstraintViolation<AssetImpl>> violations = validator.validate(asset);
        log.info("validation result:{}", violations.size());
        final StringBuilder validationMsg = new StringBuilder();
        for (ConstraintViolation<AssetImpl> violation : violations) {
            validationMsg.append("\n[")
                    .append(violation.getPropertyPath().toString())
                    .append("]:")
                    .append(violation.getMessage());
        }
        log.info("validation detail:{}", validationMsg);
    }

    @Test
    public void testFieldAndClassValidatorWithGroups() throws Exception {
        log.info("test validator with groups");
        // Create a ValidatorFactory
        // Set up a custom message interpolator with message bundles
        // Use the custom message interpolator when creating the validator
        final var now = LocalDateTime.now();
        final AssetDto asset = new AssetDto();
        asset.setId(0L);
        asset.setCreatedDate(now);
        asset.setModifiedDate(now);
        asset.setOrgId(0L); // pass the field validator
        asset.setOrgTreepath("/123/345");
        asset.setDescription("");
        asset.setStatus(1);
        asset.setName("[assetname]");

        final Set<ConstraintViolation<AssetDto>> violations = validator.validate(asset, DefaultValGroup.class, AddValGroup.class);
        log.info("validation result:{}", violations.size());
        final StringBuilder validationMsg = new StringBuilder();
        for (ConstraintViolation<AssetDto> violation : violations) {
            validationMsg.append("\n[")
                    .append(violation.getPropertyPath().toString())
                    .append("]:")
                    .append(violation.getMessage());
        }
        log.info("validation detail:{}", validationMsg);
    }

    @Test
    public void testFieldAndClassValidatorInheritance() throws Exception {
        log.info("test validator inheritance");
        // Create a ValidatorFactory
        // Set up a custom message interpolator with message bundles
        // Use the custom message interpolator when creating the validator
        final var now = LocalDateTime.now();
        final var asset = new AssetDto();
        asset.setId(0L);
        asset.setCreatedDate(now);
        asset.setModifiedDate(now);
        asset.setOrgId(null); // pass the field validator
        asset.setOrgTreepath("/123/345");
        asset.setDescription("");
        asset.setStatus(1);
        asset.setName("[assetname]");

        final ValidationResult<AssetDto> validationResult = assetService.add(SessionHolder.ANONYMOUS, asset, new LinkedHashMap<>());
        log.info("validation result:{}\n{}", validationResult.isValid(), validationResult.getValidationErrors());

//        final Set<ConstraintViolation<AssetDto>> defaultViolations = validator.validate(asset);
//        final Set<ConstraintViolation<AssetDto>> violations = validator.validate(asset, DefaultValGroup.class, AddValGroup.class);
//        log.info("validation result:{}", violations.size());
//        final StringBuilder validationMsg = new StringBuilder();
//        defaultViolations.forEach(violation -> validationMsg.append("\n[")
//                .append(violation.getPropertyPath().toString())
//                .append("]:")
//                .append(violation.getMessage()));
//        violations.forEach(violation -> validationMsg.append("\n[")
//                .append(violation.getPropertyPath().toString())
//                .append("]:")
//                .append(violation.getMessage()));
//        log.info("validation detail:{}", validationMsg);
    }
}
