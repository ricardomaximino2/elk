package com.brasajava.user.api.converter;

import com.brasajava.user.api.dto.UserRequestDTO;
import com.brasajava.user.api.dto.UserResponseDTO;
import com.brasajava.user.domain.entity.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "Spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserConverter {
    @Mapping(target = "username", expression = "java(dto.getEmail())")
    User userRequestDtoToUser(UserRequestDTO dto);

    UserResponseDTO userToUserResponseDto(User user);

}
