package com.coditas.powerbridge.service.impl;

import com.coditas.powerbridge.constants.ExceptionMessage;
import com.coditas.powerbridge.dto.request.CustomerQueryRequest;
import com.coditas.powerbridge.dto.response.CustomerQueryResponse;
import com.coditas.powerbridge.entity.Customer;
import com.coditas.powerbridge.entity.CustomerQuery;
import com.coditas.powerbridge.exception.NotFoundException;
import com.coditas.powerbridge.mapper.CustomerQueryMapper;
import com.coditas.powerbridge.repository.CustomerRepository;
import com.coditas.powerbridge.service.CustomerQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerQueryServiceImpl implements CustomerQueryService {

    private final CustomerQueryMapper customerQueryMapper;
    private final CustomerQueryRepository customerQueryRepository;
    private final CustomerRepository customerRepository;

    @Override
    public CustomerQueryResponse raiseQuery(CustomerQueryRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId()).orElseThrow(() ->
                new NotFoundException(ExceptionMessage.USER_NOT_FOUND));

        CustomerQuery customerQuery = customerQueryMapper.toCustomerQuery(request);
        customerQuery.setCustomer(customer);
        CustomerQuery savedCustomerQuery = customerQueryRepository.save(customerQuery);
        return customerQueryMapper.toCustomerQueryResponse(savedCustomerQuery);
    }
}
