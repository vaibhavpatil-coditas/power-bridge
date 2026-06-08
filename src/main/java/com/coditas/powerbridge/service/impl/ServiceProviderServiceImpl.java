package com.coditas.powerbridge.service.impl;

import com.coditas.powerbridge.dto.request.ServiceProviderRequest;
import com.coditas.powerbridge.dto.response.ServiceProviderResponse;
import com.coditas.powerbridge.entity.User;
import com.coditas.powerbridge.mapper.UserMapper;
import com.coditas.powerbridge.repository.UserRepository;
import com.coditas.powerbridge.service.ServiceProviderService;
import com.coditas.powerbridge.tenant.TenantContext;
import com.coditas.powerbridge.tenant.TenantMigrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceProviderServiceImpl implements ServiceProviderService {

    private final UserMapper userMapper;
    private final TenantMigrationService tenantMigrationService;
    private final UserRepository userRepository;

    @Override
    public ServiceProviderResponse onboard(ServiceProviderRequest request) {
        User user = userMapper.toUser(request.getUser());
        tenantMigrationService.onboardTenant(TenantContext.getCurrentTenant());
        User savedUser = userRepository.save(user);
        return ServiceProviderResponse.builder()
                .user(userMapper.toUserResponse(savedUser))
                .build();
    }
}
