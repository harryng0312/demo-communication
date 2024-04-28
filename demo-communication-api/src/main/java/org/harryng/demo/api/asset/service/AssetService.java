package org.harryng.demo.api.asset.service;

import lombok.NonNull;
import org.harryng.demo.api.asset.entity.AssetModel;
import org.harryng.demo.api.base.dto.SessionHolder;
import org.harryng.demo.api.base.service.BaseSearchableAuthenticatedService;

import java.util.Map;

public interface AssetService extends BaseSearchableAuthenticatedService<AssetModel, Long> {
    @NonNull
    AssetModel add(
            @NonNull SessionHolder sessionHolder,
            @NonNull AssetModel asset, Map<String, Object> extras) throws Exception;
}
