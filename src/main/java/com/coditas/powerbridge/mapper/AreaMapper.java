package com.coditas.powerbridge.mapper;

import com.coditas.powerbridge.dto.request.AreaRequest;
import com.coditas.powerbridge.dto.response.AreaResponse;
import com.coditas.powerbridge.entity.Area;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AreaMapper {
    Area toArea(AreaRequest request);

    @Mapping(source = "technician.id", target = "technicianId")
    @Mapping(source = "biller.id", target = "billerId")
    @Mapping(source = "city.id", target = "cityId")
    AreaResponse toAreaResponse(Area savedArea);

    List<AreaResponse> toAreaResponseList(List<Area> areas);
}
