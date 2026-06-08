package com.coditas.powerbridge.service.impl;

import com.coditas.powerbridge.common.UserPersistenceService;
import com.coditas.powerbridge.dto.request.UserRequest;
import com.coditas.powerbridge.dto.response.UserResponse;
import com.coditas.powerbridge.enums.Role;
import com.coditas.powerbridge.service.ManagementTeamMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagementTeamMemberServiceImpl implements ManagementTeamMemberService {

    private final UserPersistenceService userPersistenceService;

    @Override
    public UserResponse onboard(UserRequest userRequest) {
        return userPersistenceService.persistUser(userRequest, Role.MANAGEMENT_TEAM_MEMBER);
    }
}
