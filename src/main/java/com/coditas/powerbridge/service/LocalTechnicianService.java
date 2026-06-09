package com.coditas.powerbridge.service;

import com.coditas.powerbridge.dto.request.UserRequest;
import com.coditas.powerbridge.dto.response.UserResponse;
import jakarta.validation.Valid;

public interface LocalTechnicianService {
    UserResponse create(@Valid UserRequest request);
}
