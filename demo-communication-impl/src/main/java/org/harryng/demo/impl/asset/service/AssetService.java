package org.harryng.demo.impl.asset.service;

import org.harryng.demo.impl.asset.dto.AssetDto;
import org.harryng.demo.impl.asset.entity.AssetImpl;
import org.harryng.demo.api.util.SessionHolder;
import org.harryng.demo.impl.base.service.BaseSearchableAuthenticatedService;
import org.harryng.demo.api.util.ValidationResult;

import java.util.Map;

public interface AssetService extends BaseSearchableAuthenticatedService<AssetDto, AssetImpl, Long> {
    ValidationResult<AssetDto> add(SessionHolder sessionHolder, AssetDto asset, Map<String, Object> extras) throws Exception;
}
