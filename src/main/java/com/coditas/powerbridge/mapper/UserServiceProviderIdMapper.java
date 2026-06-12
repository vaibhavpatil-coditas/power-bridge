package com.coditas.powerbridge.mapper;

import com.coditas.powerbridge.dto.request.UserServiceProviderRequest;
import com.coditas.powerbridge.entity.UserServiceProviderId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserServiceProviderIdMapper {

    UserServiceProviderId toUserServiceProviderId(UserServiceProviderRequest request);
}
