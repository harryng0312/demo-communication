package org.harryng.demo.impl.asset.service;

import org.harryng.demo.api.util.PageInfo;
import org.harryng.demo.api.util.PageResult;
import org.harryng.demo.impl.asset.dto.AssetReq;
import org.harryng.demo.impl.asset.dto.AssetRes;
import org.harryng.demo.impl.asset.entity.AssetImpl;
import org.harryng.demo.api.util.SessionHolder;
import org.harryng.demo.impl.base.service.BaseSearchableValidatedService;
import org.harryng.demo.api.util.ValidationResult;

import java.util.Map;

public interface AssetService extends BaseSearchableValidatedService<AssetImpl, AssetRes, AssetReq, AssetReq, Long> {
    ValidationResult<AssetRes> add(SessionHolder sessionHolder, AssetReq assetReq, Map<String, Object> extras) throws Exception;

    PageResult<AssetRes> searchByName(SessionHolder sessionHolder, String keyword, PageInfo pageInfo, Map<String, Object> extras) throws Exception;
}
