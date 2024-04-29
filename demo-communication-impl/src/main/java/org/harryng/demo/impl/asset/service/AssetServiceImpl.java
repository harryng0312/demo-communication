package org.harryng.demo.impl.asset.service;

import jakarta.annotation.Resource;
import lombok.NonNull;
import org.harryng.demo.api.asset.dto.AssetDto;
import org.harryng.demo.api.asset.service.AssetService;
import org.harryng.demo.impl.asset.mapper.AssetMapper;
import org.harryng.demo.api.asset.persistence.AssetPersistence;
import org.harryng.demo.api.base.dto.SessionHolder;
import org.harryng.demo.api.asset.entity.AssetImpl;
import org.harryng.demo.impl.base.service.AbstractSearchableService;

import java.util.Map;

public class AssetServiceImpl extends AbstractSearchableService<AssetDto, AssetImpl, Long> implements AssetService {
    @Resource
    private AssetPersistence assetPersistence;

    @Resource
    private AssetMapper assetMapper;

    @Override
    public AssetPersistence getPersistence() {
        return assetPersistence;
    }

    @Override
    public AssetMapper getMapper() {
        return assetMapper;
    }

    @Override
    public @NonNull AssetDto add(
            @NonNull SessionHolder sessionHolder,
            @NonNull AssetDto asset, Map<String, Object> extras) throws Exception {
        return super.add(sessionHolder, asset, extras);
    }
}
