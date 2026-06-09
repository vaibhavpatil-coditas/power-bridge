package com.coditas.powerbridge.service.impl;

import com.coditas.powerbridge.common.UserPersistenceService;
import com.coditas.powerbridge.dto.request.UserRequest;
import com.coditas.powerbridge.dto.response.UserResponse;
import com.coditas.powerbridge.enums.Role;
import com.coditas.powerbridge.service.DistrictHeadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DistrictHeadServiceImpl implements DistrictHeadService {

    private final UserPersistenceService userPersistenceService;

    @Override
    public UserResponse create(UserRequest request) {
        return userPersistenceService.persistUser(request, Role.DISTRICT_HEAD);
    }
}
