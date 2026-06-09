package com.coditas.powerbridge.service.impl;

import com.coditas.powerbridge.common.UserPersistenceService;
import com.coditas.powerbridge.dto.request.UserRequest;
import com.coditas.powerbridge.dto.response.UserResponse;
import com.coditas.powerbridge.enums.Role;
import com.coditas.powerbridge.service.CRMService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CRMServiceImpl implements CRMService {

    private final UserPersistenceService userPersistenceService;

    @Override
    public UserResponse create(UserRequest request) {
        return userPersistenceService.persistUser(request, Role.CRM);
    }
}
