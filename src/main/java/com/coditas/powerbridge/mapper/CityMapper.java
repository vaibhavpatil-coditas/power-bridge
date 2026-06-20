package com.coditas.powerbridge.mapper;

import com.coditas.powerbridge.dto.request.CityRequest;
import com.coditas.powerbridge.dto.response.CityResponse;
import com.coditas.powerbridge.entity.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {DistrictMapper.class, UserMapper.class})
public interface CityMapper {
    City toCity(CityRequest request);

    @Mapping(source = "cityHead.id", target = "cityHeadId")
    @Mapping(source = "district.id", target = "districtId")
    CityResponse toCityResponse(City city);

    List<CityResponse> toCityResponseList(List<City> cities);
}
