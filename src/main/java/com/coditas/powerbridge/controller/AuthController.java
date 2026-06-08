package com.coditas.powerbridge.controller;

import com.coditas.powerbridge.constants.ApiPaths;
import com.coditas.powerbridge.dto.request.LoginRequest;
import com.coditas.powerbridge.dto.response.ApplicationResponse;
import com.coditas.powerbridge.dto.response.LoginResponse;
import com.coditas.powerbridge.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPaths.Auth.BASE)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(ApiPaths.Auth.LOGIN)
    public ResponseEntity<ApplicationResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok().body(ApplicationResponse.<LoginResponse>builder()
                        .success(true)
                        .message("Logged in successfully and returned jwt token")
                        .data(authService.login(loginRequest))
                        .build());
    }
}
