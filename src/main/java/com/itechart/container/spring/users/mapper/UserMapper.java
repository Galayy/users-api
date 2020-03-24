package com.itechart.container.spring.users.mapper;

import com.itechart.container.spring.users.generated.model.User;
import com.itechart.container.spring.users.generated.model.UserRole;
import com.itechart.container.spring.users.entity.UserEntity;
import com.itechart.container.spring.users.entity.enums.UserEntityRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "email", source = "login")
    User toModel(UserEntity userEntity);

    @ValueMappings({
            @ValueMapping(source = "ROLE_ADMIN", target = "ADMIN"),
            @ValueMapping(source = "ROLE_USER", target = "USER")
    })
    UserRole toModel(UserEntityRole role);

}
