package com.coditas.powerbridge.mapper;

import com.coditas.powerbridge.dto.request.UserRequest;
import com.coditas.powerbridge.dto.response.UserResponse;
import com.coditas.powerbridge.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserRequest user);

    UserResponse toUserResponse(User savedUser);

    User userResponseToUser(UserResponse userResponse);
}
