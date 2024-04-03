package org.harryng.demo.user.mapper;

import org.harryng.demo.base.mapper.DtoEntityMapperConfig;
import org.harryng.demo.user.entity.UserImpl;
import org.harryng.demo.user.entity.UserModel;
import org.harryng.demo.user.dto.UserRequest;
import org.harryng.demo.user.dto.UserResponse;
import org.mapstruct.Mapper;

@Mapper(config = DtoEntityMapperConfig.class)
public interface UserMapper {

    UserImpl map(UserRequest src);

    UserResponse map(UserModel src);

}
