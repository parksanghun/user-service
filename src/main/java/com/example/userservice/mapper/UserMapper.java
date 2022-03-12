package com.example.userservice.mapper;

import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;
import com.example.userservice.vo.RequestUser;
import com.example.userservice.vo.ResponseUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(RequestUser req);
    UserDto toUserDto(UserEntity req);
    ResponseUser toResponseUser(UserDto userDto);
    UserEntity toUserEntity(UserDto userDto);
}
