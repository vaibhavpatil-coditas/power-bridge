package com.coditas.powerbridge.service.impl;

import com.coditas.powerbridge.constants.ExceptionMessage;
import com.coditas.powerbridge.dto.request.LoginRequest;
import com.coditas.powerbridge.dto.response.LoginResponse;
import com.coditas.powerbridge.exception.UserNotAuthenticatedException;
import com.coditas.powerbridge.security.jwt.JwtUtil;
import com.coditas.powerbridge.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authenticate;

        try {
            authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getIdentity(),
                            loginRequest.getPassword()));
        }catch (UsernameNotFoundException e){
            throw new UserNotAuthenticatedException(ExceptionMessage.USER_NOT_FOUND);
        }catch (BadCredentialsException be){
            throw new UserNotAuthenticatedException(ExceptionMessage.USER_NOT_AUTHENTICATED);
        }
        UserDetails user = (UserDetails) authenticate.getPrincipal();
        if(user==null){
            throw new UserNotAuthenticatedException(ExceptionMessage.USER_NOT_AUTHENTICATED);
        }
        return new LoginResponse(jwtUtil.generateToken(user));
    }
}
