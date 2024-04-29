package org.harryng.demo.impl.base.mapper;

import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
//@Mapper(config = DtoEntityMapperConfig.class)
public interface BaseMapper <T, ET> {
    T toDto(ET source);
    ET toEntity(T source);
}
