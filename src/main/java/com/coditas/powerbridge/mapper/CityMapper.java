package com.coditas.powerbridge.mapper;

import com.coditas.powerbridge.dto.request.CityRequest;
import com.coditas.powerbridge.dto.response.CityResponse;
import com.coditas.powerbridge.entity.City;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {DistrictMapper.class, UserMapper.class})
public interface CityMapper {
    City toCity(CityRequest request);

    CityResponse toCityResponse(City city);
}
