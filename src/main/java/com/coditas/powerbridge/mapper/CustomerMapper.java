package com.coditas.powerbridge.mapper;

import com.coditas.powerbridge.dto.request.CustomerRequest;
import com.coditas.powerbridge.dto.response.CustomerResponse;
import com.coditas.powerbridge.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, AreaMapper.class})
public interface CustomerMapper {
    Customer toCustomer(CustomerRequest request);

    @Mapping(source = "area.id", target = "areaId")
    CustomerResponse toCustomerResponse(Customer customer);
}
