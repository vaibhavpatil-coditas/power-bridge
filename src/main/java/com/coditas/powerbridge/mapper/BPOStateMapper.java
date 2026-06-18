package com.coditas.powerbridge.mapper;

import com.coditas.powerbridge.dto.request.BPOStateRequest;
import com.coditas.powerbridge.dto.response.BPOStateResponse;
import com.coditas.powerbridge.entity.BPOState;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BPOStateMapper {
    BPOState toBPOState(BPOStateRequest request);

    BPOStateResponse toBPOStateResponse(BPOState savedBpoState);
}
