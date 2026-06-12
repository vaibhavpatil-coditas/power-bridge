package com.coditas.powerbridge.mapper;

import com.coditas.powerbridge.dto.request.ServiceProviderRequest;
import com.coditas.powerbridge.dto.response.ServiceProviderResponse;
import com.coditas.powerbridge.entity.ServiceProvider;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceProviderMapper {
    ServiceProvider toServiceProvider(ServiceProviderRequest request);

    ServiceProviderResponse toServiceProviderResponse(ServiceProvider savedServiceProvider);
}
