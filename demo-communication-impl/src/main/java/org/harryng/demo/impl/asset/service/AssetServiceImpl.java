package org.harryng.demo.impl.asset.service;

import jakarta.validation.ConstraintViolation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.harryng.demo.api.asset.dto.AssetDto;
import org.harryng.demo.api.asset.entity.AssetImpl;
import org.harryng.demo.api.asset.persistence.AssetPersistence;
import org.harryng.demo.api.asset.service.AssetService;
import org.harryng.demo.api.base.validator.group.AddValGroup;
import org.harryng.demo.api.util.ExtraParam;
import org.harryng.demo.api.util.SessionHolder;
import org.harryng.demo.api.util.ValidationResult;
import org.harryng.demo.impl.asset.mapper.AssetMapper;
import org.harryng.demo.impl.base.service.AbstractSearchableService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

@RequiredArgsConstructor
@Service
//@Validated
@Slf4j
public class AssetServiceImpl extends AbstractSearchableService<AssetDto, AssetImpl, Long> implements AssetService {
    private final AssetPersistence assetPersistence;
    private final AssetMapper assetMapper;

    @Override
    public AssetPersistence getPersistence() {
        return assetPersistence;
    }

    @Override
    public AssetMapper getMapper() {
        return assetMapper;
    }

    @Override
    public ValidationResult<AssetDto> add(
            @NonNull SessionHolder sessionHolder,
            @NonNull AssetDto asset, Map<String, Object> extras) throws Exception {
        final Set<ConstraintViolation<AssetDto>> validationResult = validator.validate(asset, AddValGroup.class);
        if (validationResult.isEmpty()) {
            return super.add(sessionHolder, asset, extras);
        }
        return ValidationResult.<AssetDto>builder()
                .value(asset)
                .validationErrors(ValidationResult.toValidationErrors(validationResult)).build();
    }
}