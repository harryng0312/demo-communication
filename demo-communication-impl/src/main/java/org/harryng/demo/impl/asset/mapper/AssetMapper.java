package org.harryng.demo.impl.asset.mapper;

import org.harryng.demo.impl.asset.dto.AssetDto;
import org.harryng.demo.impl.asset.entity.AssetImpl;
import org.harryng.demo.impl.base.mapper.BaseMapper;
import org.harryng.demo.impl.base.mapper.DtoEntityMapperConfig;
import org.mapstruct.Mapper;

@Mapper(config = DtoEntityMapperConfig.class)
public interface AssetMapper extends BaseMapper<AssetDto, AssetImpl> {
//    AssetDto map(AssetImpl source);

//    @InheritInverseConfiguration
//    AssetImpl map(AssetDto source);
}
