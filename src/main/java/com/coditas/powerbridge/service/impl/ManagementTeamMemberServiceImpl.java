package com.coditas.powerbridge.service.impl;

import com.coditas.powerbridge.common.ServiceUtil;
import com.coditas.powerbridge.common.UserPersistenceService;
import com.coditas.powerbridge.dto.request.UserRequest;
import com.coditas.powerbridge.dto.response.UserResponse;
import com.coditas.powerbridge.entity.User;
import com.coditas.powerbridge.enums.Role;
import com.coditas.powerbridge.mapper.UserMapper;
import com.coditas.powerbridge.repository.UserRepository;
import com.coditas.powerbridge.service.ManagementTeamMemberService;
import com.coditas.powerbridge.tenant.TenantMigrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ManagementTeamMemberServiceImpl implements ManagementTeamMemberService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TenantMigrationService tenantMigrationService;
    private final ServiceUtil serviceUtil;
    private final UserPersistenceService userPersistenceService;

    @Override
    public UserResponse onboardSalesTeamMember(UserRequest userRequest) {
        return userPersistenceService.persistUser(userRequest, Role.SALES_TEAM_MEMBER);
    }

    @Override
    public UserResponse onboardStatehead(UserRequest request) {
        return userPersistenceService.persistUser(request, Role.STATE_HEAD);
    }
}
