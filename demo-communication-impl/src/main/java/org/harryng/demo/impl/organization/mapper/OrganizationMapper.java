package org.harryng.demo.impl.organization.mapper;

import org.harryng.demo.impl.base.mapper.BaseMapper;
import org.harryng.demo.impl.base.mapper.DtoEntityMapperConfig;
import org.harryng.demo.impl.organization.dto.OrganizationDto;
import org.harryng.demo.impl.organization.entity.OrganizationImpl;
import org.mapstruct.Mapper;

@Mapper(config = DtoEntityMapperConfig.class)
public interface OrganizationMapper extends BaseMapper<OrganizationImpl, OrganizationDto, OrganizationDto, OrganizationDto> {

}
