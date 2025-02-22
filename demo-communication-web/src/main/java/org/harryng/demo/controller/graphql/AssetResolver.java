package org.harryng.demo.controller.graphql;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.harryng.demo.api.util.*;
import org.harryng.demo.impl.asset.dto.AssetReq;
import org.harryng.demo.impl.asset.dto.AssetRes;
import org.harryng.demo.impl.asset.service.AssetService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Map;
import java.util.Optional;

@Controller
public class AssetResolver {
    private final AssetService assetService;

    public AssetResolver(AssetService assetService) {
        this.assetService = assetService;
    }

    @Resource
    private HttpServletRequest httpServletRequest;

    @QueryMapping
    public PageResult<AssetRes> searchAssetByName(@Argument String keyword, @Argument PageInfo pageInfo) throws Exception {
        final String token = SessionUtil.getToken(httpServletRequest);
        final SessionHolder sessionHolder = SessionUtil.getSessionHolderFromAccessToken(token);
        return assetService.searchByName(sessionHolder, keyword, pageInfo, Map.of());
    }

    @QueryMapping
    public Optional<AssetRes> getAssetById(@Argument long id) throws Exception {
        final String token = SessionUtil.getToken(httpServletRequest);
        final SessionHolder sessionHolder = SessionUtil.getSessionHolderFromAccessToken(token);
        return assetService.getById(sessionHolder, id, Map.of());
    }

    @MutationMapping()
    public ValidationResult<AssetRes> addAsset(@Argument AssetReq asset) throws Exception {
        final String token = SessionUtil.getToken(httpServletRequest);
        final SessionHolder sessionHolder = SessionUtil.getSessionHolderFromAccessToken(token);
        return assetService.add(sessionHolder, asset, Map.of());
    }
}
