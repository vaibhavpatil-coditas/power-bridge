package com.coditas.powerbridge.mapper;

import com.coditas.powerbridge.dto.request.AreaRequest;
import com.coditas.powerbridge.dto.response.AreaResponse;
import com.coditas.powerbridge.entity.Area;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AreaMapper {
    Area toArea(AreaRequest request);

    AreaResponse toAreaResponse(Area savedArea);
}
