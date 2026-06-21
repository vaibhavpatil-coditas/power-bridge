package com.coditas.powerbridge.mapper;

import com.coditas.powerbridge.dto.request.BillerQueryRequest;
import com.coditas.powerbridge.dto.response.BillerQueryResponse;
import com.coditas.powerbridge.entity.BillerQuery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BillerQueryMapper {
    BillerQuery toBillerQuery(BillerQueryRequest request);
    @Mapping(source = "biller.id", target = "billerId")
    BillerQueryResponse toBillerQueryResponse(BillerQuery savedBillerQuery);

    List<BillerQueryResponse> toBillerQueryResponseList(List<BillerQuery> queries);
}
