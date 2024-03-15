package org.harryng.demo.user.mapper;

import org.harryng.demo.base.mapper.DtoEntityMapperConfig;
import org.harryng.demo.user.pojo.data.entity.UserImpl;
import org.harryng.demo.user.pojo.data.model.UserModel;
import org.harryng.demo.user.pojo.dto.UserRequest;
import org.harryng.demo.user.pojo.dto.UserResponse;
import org.mapstruct.Mapper;

@Mapper(config = DtoEntityMapperConfig.class)
public interface UserMapper {

    UserImpl map(UserRequest src);

    UserResponse map(UserModel src);

}
