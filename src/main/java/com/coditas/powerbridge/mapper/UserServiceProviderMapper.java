package com.coditas.powerbridge.mapper;

import com.coditas.powerbridge.dto.request.UserServiceProviderRequest;
import com.coditas.powerbridge.dto.response.UserServiceProviderReponse;
import com.coditas.powerbridge.entity.UserServiceProvider;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, ServiceProviderMapper.class})
public interface UserServiceProviderMapper {
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "serviceProviderId", target = "serviceProvider.id")
    UserServiceProvider toUserServiceProvider(UserServiceProviderRequest request);

    UserServiceProviderReponse toUserServiceProviderReponse(UserServiceProvider savedUserServiceProvider);
}
