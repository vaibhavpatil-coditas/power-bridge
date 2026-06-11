package com.coditas.powerbridge.mapper;

import com.coditas.powerbridge.dto.request.CustomerRequest;
import com.coditas.powerbridge.dto.response.CustomerResponse;
import com.coditas.powerbridge.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class, AreaMapper.class})
public interface CustomerMapper {
    Customer toCustomer(CustomerRequest request);

    CustomerResponse toCustomerResponse(Customer customer);
}
