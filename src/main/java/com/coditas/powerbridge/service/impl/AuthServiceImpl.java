package com.coditas.powerbridge.service.impl;

import com.coditas.powerbridge.constants.ExceptionMessage;
import com.coditas.powerbridge.dto.request.LoginRequest;
import com.coditas.powerbridge.dto.response.LoginResponse;
import com.coditas.powerbridge.exception.UserNotAuthenticatedException;
import com.coditas.powerbridge.security.jwt.JwtUtil;
import com.coditas.powerbridge.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getIdentity(),
                        loginRequest.getPassword()));
        UserDetails user = (UserDetails) authenticate.getPrincipal();
        if(user==null){
            throw new UserNotAuthenticatedException(ExceptionMessage.USER_NOT_AUTHENTICATED);
        }
        return new LoginResponse(jwtUtil.generateToken(user));
    }
}
