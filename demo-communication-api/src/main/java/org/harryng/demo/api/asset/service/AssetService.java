package org.harryng.demo.api.asset.service;

import org.harryng.demo.api.asset.dto.AssetDto;
import org.harryng.demo.api.asset.entity.AssetImpl;
import org.harryng.demo.api.base.dto.SessionHolder;
import org.harryng.demo.api.base.service.BaseSearchableAuthenticatedService;

import java.util.Map;

public interface AssetService extends BaseSearchableAuthenticatedService<AssetDto, AssetImpl, Long> {
    AssetDto add(SessionHolder sessionHolder, AssetDto asset, Map<String, Object> extras) throws Exception;
}
