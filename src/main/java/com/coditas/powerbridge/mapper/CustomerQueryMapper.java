package com.coditas.powerbridge.mapper;

import com.coditas.powerbridge.dto.request.CustomerQueryRequest;
import com.coditas.powerbridge.dto.response.CustomerQueryResponse;
import com.coditas.powerbridge.entity.CustomerQuery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerQueryMapper{
    @Mapping(source = "customerId", target = "customer.id")
    CustomerQuery toCustomerQuery(CustomerQueryRequest request);

    CustomerQueryResponse toCustomerQueryResponse(CustomerQuery savedCustomerQuery);
}
