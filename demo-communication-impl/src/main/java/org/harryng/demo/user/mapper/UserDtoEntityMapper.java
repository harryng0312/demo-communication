package org.harryng.demo.user.mapper;

import org.harryng.demo.base.mapper.AbstractDtoEntityMapper;
import org.harryng.demo.user.pojo.data.entity.UserImpl;
import org.harryng.demo.user.pojo.data.model.UserModel;
import org.harryng.demo.user.pojo.dto.UserRequest;
import org.harryng.demo.user.pojo.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = AbstractDtoEntityMapper.class)
public interface UserDtoEntityMapper {

    @Mapping(target = "username", source = "username")
    @Mapping(target = "screenName", source = "screenName")
    @Mapping(target = "dob", source = "dob")
    UserImpl toEntity(UserRequest src);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "screenName", source = "screenName")
    @Mapping(target = "dob", source = "dob")
    UserResponse toResponseDto(UserModel src);

}
