package com.coditas.powerbridge.mapper;

import com.coditas.powerbridge.dto.request.StateRequest;
import com.coditas.powerbridge.dto.response.StateResponse;
import com.coditas.powerbridge.entity.State;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StateMapper {
    State toState(StateRequest request);

    StateResponse toStateResponse(State savedState);
}
