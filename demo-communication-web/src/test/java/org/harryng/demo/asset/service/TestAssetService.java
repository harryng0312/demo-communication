package org.harryng.demo.asset.service;

import jakarta.annotation.Resource;
import jakarta.validation.*;
import jakarta.validation.executable.ExecutableValidator;
import lombok.extern.slf4j.Slf4j;
import org.harryng.demo.Application;
import org.harryng.demo.impl.asset.entity.AssetImpl;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.Set;

@SpringBootTest(classes = Application.class)
@Import(Application.class)
@Slf4j
public class TestAssetService {

    @Resource
    private Validator validator;

    @Test
    public void testFieldAndClassHibernateValidatorBasic() {
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
    public void testFieldAndClassValidatorSpring() {
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
        asset.setOrgTreepath("/");
        asset.setDescription("");
        asset.setStatus(1);
        asset.setName("[assetname]");


        final Set<ConstraintViolation<AssetImpl>> violations = validator.validate(asset);
        log.info("validation result:{}", violations.size());
        log.info("validation detail:{}", violations);
    }
}
