package com.coditas.powerbridge.service.impl;

import com.coditas.powerbridge.common.UserPersistenceService;
import com.coditas.powerbridge.constants.ExceptionMessage;
import com.coditas.powerbridge.dto.request.CustomerRequest;
import com.coditas.powerbridge.dto.response.CustomerResponse;
import com.coditas.powerbridge.dto.response.UserResponse;
import com.coditas.powerbridge.entity.Area;
import com.coditas.powerbridge.entity.Customer;
import com.coditas.powerbridge.enums.Role;
import com.coditas.powerbridge.exception.NotFoundException;
import com.coditas.powerbridge.mapper.CustomerMapper;
import com.coditas.powerbridge.mapper.UserMapper;
import com.coditas.powerbridge.repository.AreaRepository;
import com.coditas.powerbridge.repository.CustomerRepository;
import com.coditas.powerbridge.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final UserPersistenceService userPersistenceService;
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final AreaRepository areaRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public CustomerResponse create(CustomerRequest request) {
        Area area = areaRepository.findById(request.getArea().getId()).orElseThrow(() ->
                new NotFoundException(ExceptionMessage.AREA_NOT_FOUND));
        UserResponse userResponse = userPersistenceService.persistUser(request.getUser(), Role.CUSTOMER);
        Customer customer = customerMapper.toCustomer(request);
        customer.setArea(area);
        customer.setUser(userMapper.userResponseToUser(userResponse));
        Customer savedCustomer = customerRepository.save(customer);
        CustomerResponse customerResponse = customerMapper.toCustomerResponse(savedCustomer);
        customerResponse.setUser(userResponse);
        return customerResponse;
    }
}
