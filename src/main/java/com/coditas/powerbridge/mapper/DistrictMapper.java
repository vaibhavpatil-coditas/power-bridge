package com.coditas.powerbridge.mapper;

import com.coditas.powerbridge.dto.request.DistrictRequest;
import com.coditas.powerbridge.dto.response.DistrictResponse;
import com.coditas.powerbridge.entity.District;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DistrictMapper {

    District toDistrict(DistrictRequest request);

    DistrictResponse toDistrictResponse(District savedDistrict);
}
