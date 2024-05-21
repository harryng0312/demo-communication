package org.harryng.demo.impl.organization.mapper;

import lombok.NonNull;
import org.harryng.demo.impl.organization.dto.OrganizationDto;
import org.harryng.demo.impl.organization.entity.OrganizationImpl;
import org.harryng.demo.impl.base.mapper.BaseMapper;
import org.harryng.demo.impl.base.mapper.DtoEntityMapperConfig;
import org.mapstruct.Mapper;

@Mapper(config = DtoEntityMapperConfig.class)
public interface OrganizationMapper extends BaseMapper<OrganizationDto, OrganizationImpl> {
    @Override
    OrganizationDto toDto(@NonNull OrganizationImpl source);
    @Override
    OrganizationImpl toEntity(@NonNull OrganizationDto source);
}
