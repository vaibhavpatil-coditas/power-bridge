package com.coditas.powerbridge.common;

import com.coditas.powerbridge.constants.ExceptionMessage;
import com.coditas.powerbridge.dto.response.CustomerQueryResponse;
import com.coditas.powerbridge.entity.CustomerQuery;
import com.coditas.powerbridge.entity.Employee;
import com.coditas.powerbridge.enums.QueryStatus;
import com.coditas.powerbridge.exception.NotFoundException;
import com.coditas.powerbridge.mapper.CustomerQueryMapper;
import com.coditas.powerbridge.repository.EmployeeRepository;
import com.coditas.powerbridge.service.impl.CustomerQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class TenantSwitchUtil {

    private final EmployeeRepository employeeRepository;
    private final CustomerQueryRepository customerQueryRepository;
    private final CustomerQueryMapper customerQueryMapper;

    public void saveEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    public CustomerQueryResponse updateQueryStatus(Long queryId, QueryStatus status) {
        CustomerQuery customerQuery = customerQueryRepository.findById(queryId).orElseThrow(() ->
                new NotFoundException(ExceptionMessage.CUSTOMER_QUERY_NOT_FOUND));
        customerQuery.setStatus(status);
        CustomerQuery savedCustomerQuery = customerQueryRepository.save(customerQuery);
        return customerQueryMapper.toCustomerQueryResponse(savedCustomerQuery);
    }
}
