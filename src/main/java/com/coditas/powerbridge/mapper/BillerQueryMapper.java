package com.coditas.powerbridge.mapper;

import com.coditas.powerbridge.dto.request.BillerQueryRequest;
import com.coditas.powerbridge.dto.response.BillerQueryResponse;
import com.coditas.powerbridge.entity.BillerQuery;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BillerQueryMapper {
    BillerQuery toBillerQuery(BillerQueryRequest request);

    BillerQueryResponse toBillerQueryResponse(BillerQuery savedBillerQuery);
}
