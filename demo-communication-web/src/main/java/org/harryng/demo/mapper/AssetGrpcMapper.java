package org.harryng.demo.mapper;

import org.harryng.demo.controller.grpc.asset.AssetDtoGrpc;
import org.harryng.demo.impl.asset.dto.AssetDto;
import org.harryng.demo.impl.base.mapper.BaseGrpcMapper;
import org.harryng.demo.impl.base.mapper.DtoEntityMapperConfig;
import org.mapstruct.Mapper;

@Mapper(config = DtoEntityMapperConfig.class)
public interface AssetGrpcMapper extends BaseGrpcMapper<AssetDto, AssetDtoGrpc> {
//    AssetDto map(AssetImpl source);

//    @InheritInverseConfiguration
//    AssetImpl map(AssetDto source);
}
