package com.coditas.powerbridge.mapper;

import com.coditas.powerbridge.dto.request.DistrictRequest;
import com.coditas.powerbridge.dto.response.DistrictResponse;
import com.coditas.powerbridge.entity.District;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DistrictMapper {

    District toDistrict(DistrictRequest request);

    @Mapping(source = "state.id", target = "stateId")
    @Mapping(source = "districtHead.id", target = "districtHeadId")
    DistrictResponse toDistrictResponse(District savedDistrict);

    List<DistrictResponse> toDistrictResponseList(List<District> districts);
}
