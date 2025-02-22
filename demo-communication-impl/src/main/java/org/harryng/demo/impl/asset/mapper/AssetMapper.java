package org.harryng.demo.impl.asset.mapper;

import org.harryng.demo.impl.asset.dto.AssetReq;
import org.harryng.demo.impl.asset.dto.AssetRes;
import org.harryng.demo.impl.asset.entity.AssetImpl;
import org.harryng.demo.impl.base.mapper.BaseMapper;
import org.harryng.demo.impl.base.mapper.DtoEntityMapperConfig;
import org.mapstruct.Mapper;

@Mapper(config = DtoEntityMapperConfig.class)
public interface AssetMapper extends BaseMapper<AssetImpl, AssetRes, AssetReq, AssetReq> {
//    AssetDto map(AssetImpl source);

//    @InheritInverseConfiguration
//    AssetImpl map(AssetDto source);
}
