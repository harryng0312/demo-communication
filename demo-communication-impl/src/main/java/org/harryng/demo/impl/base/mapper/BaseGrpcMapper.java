package org.harryng.demo.impl.base.mapper;

import lombok.NonNull;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
//@Mapper(config = DtoEntityMapperConfig.class)
public interface BaseGrpcMapper<T, GT> {
    T toDto(@NonNull GT source);
    GT toGrpc(@NonNull T source);
}
