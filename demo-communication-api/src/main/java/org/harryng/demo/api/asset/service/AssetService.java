package org.harryng.demo.api.asset.service;

import org.harryng.demo.api.asset.dto.AssetDto;
import org.harryng.demo.api.asset.entity.AssetImpl;
import org.harryng.demo.api.util.SessionHolder;
import org.harryng.demo.api.base.service.BaseSearchableAuthenticatedService;
import org.harryng.demo.api.util.ValidationResult;

import java.util.Map;

public interface AssetService extends BaseSearchableAuthenticatedService<AssetDto, AssetImpl, Long> {
    ValidationResult<AssetDto> add(SessionHolder sessionHolder, AssetDto asset, Map<String, Object> extras) throws Exception;
}
