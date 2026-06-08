package com.coditas.powerbridge.service.impl;

import com.coditas.powerbridge.common.ServiceUtil;
import com.coditas.powerbridge.common.UserPersistenceService;
import com.coditas.powerbridge.dto.request.UserRequest;
import com.coditas.powerbridge.dto.response.UserResponse;
import com.coditas.powerbridge.enums.Role;
import com.coditas.powerbridge.mapper.UserMapper;
import com.coditas.powerbridge.repository.UserRepository;
import com.coditas.powerbridge.service.SuperAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SuperAdminServiceImpl implements SuperAdminService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final ServiceUtil serviceUtil;
    private final UserPersistenceService userPersistenceService;

    @Override
    public UserResponse onboardManagementTeamMember(UserRequest userRequest) {
        return userPersistenceService.persistUser(userRequest, Role.MANAGEMENT_TEAM_MEMBER);
    }

}
