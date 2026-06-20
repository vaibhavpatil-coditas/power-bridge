package com.coditas.powerbridge.common;

import com.coditas.powerbridge.constants.ExceptionMessage;
import com.coditas.powerbridge.dto.response.CustomerQueryResponse;
import com.coditas.powerbridge.entity.CustomerQuery;
import com.coditas.powerbridge.entity.Employee;
import com.coditas.powerbridge.enums.QueryStatus;
import com.coditas.powerbridge.exception.NotFoundException;
import com.coditas.powerbridge.mapper.CustomerQueryMapper;
import com.coditas.powerbridge.repository.EmployeeRepository;
import com.coditas.powerbridge.repository.CustomerQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class TenantUtil {

    private final EmployeeRepository employeeRepository;
    private final CustomerQueryRepository customerQueryRepository;
    private final CustomerQueryMapper customerQueryMapper;

    public void saveEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    public CustomerQuery getQuery(Long queryId, QueryStatus status) {
        CustomerQuery customerQuery = customerQueryRepository.findById(queryId).orElseThrow(() ->
                new NotFoundException(ExceptionMessage.CUSTOMER_QUERY_NOT_FOUND));
        customerQuery.setStatus(status);
        return customerQuery;
    }

    public CustomerQueryResponse resolveQuery(Long queryId, QueryStatus queryStatus) {
        CustomerQuery customerQuery = getQuery(queryId, queryStatus);
        customerQuery.setResolvedDate(Instant.now());
        CustomerQuery savedCustomerQuery = customerQueryRepository.save(customerQuery);
        return customerQueryMapper.toCustomerQueryResponse(savedCustomerQuery);
    }

    public CustomerQueryResponse escalateToManager1(Long queryId, QueryStatus queryStatus) {
        CustomerQuery customerQuery = getQuery(queryId, queryStatus);
        customerQuery.setFirstEscalatedOn(Instant.now());
        CustomerQuery savedCustomerQuery = customerQueryRepository.save(customerQuery);
        return customerQueryMapper.toCustomerQueryResponse(savedCustomerQuery);
    }

    public CustomerQueryResponse escalateToManager2(Long queryId, QueryStatus queryStatus) {
        CustomerQuery customerQuery = getQuery(queryId, queryStatus);
        customerQuery.setSecondEscalatedOn(Instant.now());
        CustomerQuery savedCustomerQuery = customerQueryRepository.save(customerQuery);
        return customerQueryMapper.toCustomerQueryResponse(savedCustomerQuery);
    }
}
