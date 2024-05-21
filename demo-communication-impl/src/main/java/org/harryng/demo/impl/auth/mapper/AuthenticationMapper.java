package org.harryng.demo.impl.auth.mapper;

import org.harryng.demo.impl.auth.dto.AuthenticationInfo;
import org.harryng.demo.api.util.SessionHolder;
import org.harryng.demo.impl.base.mapper.DtoEntityMapperConfig;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = DtoEntityMapperConfig.class)
public interface AuthenticationMapper {
    @Mapping(target = "id", source = "sessionId")
    @Mapping(target = "username", source = "username")
    AuthenticationInfo map(SessionHolder source);

    @InheritInverseConfiguration
    SessionHolder map(AuthenticationInfo source);
}
