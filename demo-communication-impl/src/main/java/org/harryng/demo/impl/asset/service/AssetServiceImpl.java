package org.harryng.demo.impl.asset.service;

import jakarta.annotation.Resource;
import lombok.NonNull;
import org.harryng.demo.api.asset.entity.AssetModel;
import org.harryng.demo.api.asset.persistence.AssetPersistence;
import org.harryng.demo.api.asset.service.AssetService;
import org.harryng.demo.api.base.dto.SessionHolder;
import org.harryng.demo.impl.asset.entity.AssetImpl;
import org.harryng.demo.impl.base.service.AbstractSearchableService;

import java.util.Map;

public class AssetServiceImpl extends AbstractSearchableService<AssetModel, Long> implements AssetService {
    @Resource
    private AssetPersistence assetPersistence;

    @Override
    public AssetPersistence getPersistence() {
        return assetPersistence;
    }

    @Override
    public @NonNull AssetModel add(
            @NonNull SessionHolder sessionHolder,
            @NonNull AssetModel asset, Map<String, Object> extras) throws Exception {
        return super.add(sessionHolder, asset, extras);
    }
}
