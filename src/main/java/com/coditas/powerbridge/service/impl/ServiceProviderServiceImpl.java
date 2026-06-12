package com.coditas.powerbridge.service.impl;

import com.coditas.powerbridge.dto.request.ServiceProviderRequest;
import com.coditas.powerbridge.dto.response.ServiceProviderResponse;
import com.coditas.powerbridge.entity.ServiceProvider;
import com.coditas.powerbridge.entity.User;
import com.coditas.powerbridge.mapper.ServiceProviderMapper;
import com.coditas.powerbridge.repository.ServiceProviderRepository;
import com.coditas.powerbridge.repository.UserRepository;
import com.coditas.powerbridge.service.ServiceProviderService;
import com.coditas.powerbridge.tenant.TenantContext;
import com.coditas.powerbridge.tenant.TenantMigrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ServiceProviderServiceImpl implements ServiceProviderService {

    private final TenantMigrationService tenantMigrationService;
    private final ServiceProviderMapper serviceProviderMapper;
    private final ServiceProviderRepository serviceProviderRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ServiceProviderResponse onboard(ServiceProviderRequest request) {
        ServiceProvider serviceProvider = serviceProviderMapper.toServiceProvider(request);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        User onBoardingMember = (User) authentication.getPrincipal();

        serviceProvider.setUser(onBoardingMember);
        serviceProvider.setCreatedAt(Instant.now());
        tenantMigrationService.onboardTenant(TenantContext.getCurrentTenant());
        ServiceProvider savedServiceProvider = serviceProviderRepository.save(serviceProvider);
        return serviceProviderMapper.toServiceProviderResponse(savedServiceProvider);
    }
}
