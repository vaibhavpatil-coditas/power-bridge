package com.coditas.powerbridge.service;

import com.coditas.powerbridge.dto.request.LoginRequest;
import com.coditas.powerbridge.dto.response.LoginResponse;
import jakarta.validation.Valid;

public interface AuthService {
    LoginResponse login(@Valid LoginRequest loginRequest);
}
