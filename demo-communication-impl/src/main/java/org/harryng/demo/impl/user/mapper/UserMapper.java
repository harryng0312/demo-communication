package org.harryng.demo.impl.user.mapper;

import org.harryng.demo.impl.user.dto.UserDto;
import org.harryng.demo.impl.base.mapper.BaseMapper;
import org.harryng.demo.impl.base.mapper.DtoEntityMapperConfig;
import org.harryng.demo.impl.user.entity.UserImpl;
import org.harryng.demo.impl.user.entity.UserModel;
import org.harryng.demo.impl.user.dto.UserRequest;
import org.harryng.demo.impl.user.dto.UserResponse;
import org.mapstruct.Mapper;

@Mapper(config = DtoEntityMapperConfig.class)
public interface UserMapper extends BaseMapper<UserDto, UserImpl> {

    UserImpl map(UserRequest src);
//
    UserResponse map(UserModel src);

    UserResponse map(UserDto src);

}
