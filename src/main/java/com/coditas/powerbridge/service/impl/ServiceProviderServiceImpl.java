package com.coditas.powerbridge.service.impl;

import com.coditas.powerbridge.common.TenantSwitchUtil;
import com.coditas.powerbridge.constants.ExceptionMessage;
import com.coditas.powerbridge.dto.request.ServiceProviderRequest;
import com.coditas.powerbridge.dto.response.ServiceProviderResponse;
import com.coditas.powerbridge.entity.Employee;
import com.coditas.powerbridge.entity.ServiceProvider;
import com.coditas.powerbridge.entity.User;
import com.coditas.powerbridge.enums.Role;
import com.coditas.powerbridge.exception.ResourceAlreadyExistException;
import com.coditas.powerbridge.mapper.EmployeeMapper;
import com.coditas.powerbridge.mapper.ServiceProviderMapper;
import com.coditas.powerbridge.repository.EmployeeRepository;
import com.coditas.powerbridge.repository.ServiceProviderRepository;
import com.coditas.powerbridge.service.ServiceProviderService;
import com.coditas.powerbridge.tenant.TenantContext;
import com.coditas.powerbridge.tenant.TenantMigrationService;
import com.coditas.powerbridge.tenant.TenantSchemaConnectionProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ServiceProviderServiceImpl implements ServiceProviderService {

    private final TenantMigrationService tenantMigrationService;
    private final ServiceProviderMapper serviceProviderMapper;
    private final ServiceProviderRepository serviceProviderRepository;
    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final TenantSchemaConnectionProvider tenantSchemaConnectionProvider;
    private final TenantSwitchUtil tenantSwitchUtil;

    @Override
    @Transactional
    public ServiceProviderResponse onboard(ServiceProviderRequest request) {
        ServiceProvider serviceProvider = serviceProviderMapper.toServiceProvider(request);

        if(serviceProviderRepository.existsByName(request.getName())){
            throw new ResourceAlreadyExistException(ExceptionMessage.SERVICE_PROVIDER_ALREADY_EXIST);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        User onBoardingMember = (User) authentication.getPrincipal();

        Employee employee = employeeMapper.toEmployee(request.getOwner());
        employee.setRole(Role.SERVICE_PROVIDER);
        employee.setPassword(passwordEncoder.encode(request.getOwner().getPassword()));
        employee.setCreatedAt(Instant.now());

        serviceProvider.setUser(onBoardingMember);
        serviceProvider.setCreatedAt(Instant.now());

        String emailDomain = request.getOwner().getEmail().split("@")[1].split("\\.")[0];

        tenantMigrationService.onboardTenant(emailDomain);
        ServiceProvider savedServiceProvider = serviceProviderRepository.save(serviceProvider);
        TenantContext.setCurrentTenant(emailDomain);
        tenantSwitchUtil.saveEmployee(employee);
        return serviceProviderMapper.toServiceProviderResponse(savedServiceProvider);
    }
}
