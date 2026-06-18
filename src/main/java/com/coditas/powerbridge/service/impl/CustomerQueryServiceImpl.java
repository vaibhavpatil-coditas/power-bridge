package com.coditas.powerbridge.service.impl;

import com.coditas.powerbridge.common.TenantSwitchUtil;
import com.coditas.powerbridge.constants.ExceptionMessage;
import com.coditas.powerbridge.dto.request.CustomerQueryRequest;
import com.coditas.powerbridge.dto.request.TenantRequest;
import com.coditas.powerbridge.dto.response.CustomerQueryResponse;
import com.coditas.powerbridge.entity.Customer;
import com.coditas.powerbridge.entity.CustomerQuery;
import com.coditas.powerbridge.enums.QueryStatus;
import com.coditas.powerbridge.exception.NotFoundException;
import com.coditas.powerbridge.mapper.CustomerQueryMapper;
import com.coditas.powerbridge.repository.CustomerRepository;
import com.coditas.powerbridge.service.CustomerQueryService;
import com.coditas.powerbridge.tenant.TenantContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerQueryServiceImpl implements CustomerQueryService {

    private final CustomerQueryMapper customerQueryMapper;
    private final CustomerQueryRepository customerQueryRepository;
    private final CustomerRepository customerRepository;
    private final TenantSwitchUtil tenantSwitchUtil;

    @Override
    public CustomerQueryResponse raiseQuery(CustomerQueryRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId()).orElseThrow(() ->
                new NotFoundException(ExceptionMessage.USER_NOT_FOUND));

        CustomerQuery customerQuery = customerQueryMapper.toCustomerQuery(request);
        customerQuery.setCustomer(customer);
        customerQuery.setStatus(QueryStatus.PENDING);
        CustomerQuery savedCustomerQuery = customerQueryRepository.save(customerQuery);
        return customerQueryMapper.toCustomerQueryResponse(savedCustomerQuery);
    }

    @Override
    public CustomerQueryResponse resolveQuery(Long queryId, TenantRequest request) {
        TenantContext.setCurrentTenant(request.getTenantId());
        return tenantSwitchUtil.updateQueryStatus(queryId, QueryStatus.RESOLVED);
    }

    @Override
    public CustomerQueryResponse escalateToManager1(Long queryId, TenantRequest request) {
        TenantContext.setCurrentTenant(request.getTenantId());
        return tenantSwitchUtil.updateQueryStatus(queryId, QueryStatus.ESCALATED_M1);
    }

    @Override
    public CustomerQueryResponse escalateToManager2(Long queryId) {
        return tenantSwitchUtil.updateQueryStatus(queryId, QueryStatus.ESCALATED_M2);
    }
}