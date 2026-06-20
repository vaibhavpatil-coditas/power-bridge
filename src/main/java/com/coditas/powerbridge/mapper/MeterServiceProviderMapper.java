package com.coditas.powerbridge.mapper;

import com.coditas.powerbridge.dto.request.MeterServiceProviderRequest;
import com.coditas.powerbridge.dto.response.MeterServiceProviderResponse;
import com.coditas.powerbridge.entity.MeterServiceProvider;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MeterServiceProviderMapper {
    @Mapping(source = "serviceProviderId", target = "serviceProvider.id")
    MeterServiceProvider toMeterServiceProvider(MeterServiceProviderRequest request);

    MeterServiceProviderResponse toMeterServiceProviderResponse(MeterServiceProvider savedResponse);
}
