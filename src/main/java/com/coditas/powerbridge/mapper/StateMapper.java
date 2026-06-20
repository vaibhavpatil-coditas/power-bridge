package com.coditas.powerbridge.mapper;

import com.coditas.powerbridge.dto.request.StateRequest;
import com.coditas.powerbridge.dto.response.StateResponse;
import com.coditas.powerbridge.entity.State;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StateMapper {
    State toState(StateRequest request);

    @Mapping(source = "stateHead.id", target = "stateHeadId")
    StateResponse toStateResponse(State savedState);

    List<StateResponse> toStateResponseList(List<State> states);
}
