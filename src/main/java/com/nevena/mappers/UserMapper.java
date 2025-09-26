package com.nevena.mappers;

import com.nevena.dto.user.UserDto;
import com.nevena.entities.Role;
import com.nevena.entities.User;
import com.nevena.mappers.config.CentralMapperConfig;
import org.mapstruct.Mapper;

import java.util.stream.Collectors;

@Mapper(config = CentralMapperConfig.class)
public interface UserMapper {
    default UserDto toDto(User user) {
        return new UserDto(
                user.getEmail(),
                user.getJmbg(),
                user.getName(),
                user.getSurname(),
                user.getRoles().stream().map(Role::getName).collect(Collectors.toSet())
        );
    }


}
