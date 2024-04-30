package org.harryng.demo.impl.asset.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.harryng.demo.api.asset.dto.AssetDto;
import org.harryng.demo.api.asset.entity.AssetImpl;
import org.harryng.demo.api.asset.persistence.AssetPersistence;
import org.harryng.demo.api.asset.service.AssetService;
import org.harryng.demo.api.base.dto.SessionHolder;
import org.harryng.demo.impl.asset.mapper.AssetMapper;
import org.harryng.demo.impl.base.service.AbstractSearchableService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Map;

@RequiredArgsConstructor
@Service
@Validated
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
//    @NotNull(groups = {AddValGroup.class})
    public AssetDto add(
            @NonNull SessionHolder sessionHolder,
            @NonNull AssetDto asset, Map<String, Object> extras) throws Exception {
        log.info("===== Insert successfully =====");
        return asset;
//        return super.add(sessionHolder, asset, extras);
    }
}