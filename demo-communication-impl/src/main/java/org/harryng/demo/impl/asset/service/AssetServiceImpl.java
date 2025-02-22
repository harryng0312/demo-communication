package org.harryng.demo.impl.asset.service;

import jakarta.validation.ConstraintViolation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.harryng.demo.api.util.*;
import org.harryng.demo.impl.asset.dto.AssetReq;
import org.harryng.demo.impl.asset.dto.AssetRes;
import org.harryng.demo.impl.asset.entity.AssetImpl;
import org.harryng.demo.impl.asset.mapper.AssetMapper;
import org.harryng.demo.impl.asset.persistence.AssetPersistence;
import org.harryng.demo.impl.base.service.AbstractSearchableService;
import org.harryng.demo.impl.base.validator.group.AddValGroup;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
@Service
@Slf4j
//@Validated
public class AssetServiceImpl extends AbstractSearchableService<AssetImpl, AssetRes, AssetReq, AssetReq, Long>
        implements AssetService {
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
    public ValidationResult<AssetRes> add(
            @NonNull SessionHolder sessionHolder,
            @NonNull AssetReq assetReq, Map<String, Object> extras) throws Exception {
        final var result = new ValidationResult<AssetRes>();
        final Set<ConstraintViolation<AssetReq>> validationResult = validator.validate(assetReq, AddValGroup.class);
        if (validationResult.isEmpty()) {
            return super.add(sessionHolder, assetReq, extras);
        } else {
            final List<ValidationError> validationErrors = validationResult.stream()
                    .map(dtoConstraintViolation -> ValidationError.of(
                            dtoConstraintViolation.getPropertyPath().toString(),
                            dtoConstraintViolation.getMessage(),
                            dtoConstraintViolation.getInvalidValue()))
                    .toList();
            result.getValidationErrors().addAll(validationErrors);
        }
        return result;
    }

    @Override
    public PageResult<AssetRes> searchByName(@NonNull SessionHolder sessionHolder, String keyword, PageInfo pageInfo, Map<String, Object> extras) throws Exception {
        final var result = new PageResult<AssetRes>(pageInfo);
        final var pageAssetImpl = assetPersistence.findByNameContainingIgnoreCase(keyword, pageInfo.toPageable());
        result.fromPage(pageAssetImpl, getMapper()::convertEntToGetDto);
        return result;
    }
}