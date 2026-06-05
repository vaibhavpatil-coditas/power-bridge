package com.coditas.powerbridge.service.impl;

import com.coditas.powerbridge.dto.request.UserRequest;
import com.coditas.powerbridge.dto.response.UserResponse;
import com.coditas.powerbridge.entity.User;
import com.coditas.powerbridge.enums.Role;
import com.coditas.powerbridge.mapper.UserMapper;
import com.coditas.powerbridge.repository.UserRepository;
import com.coditas.powerbridge.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse onboardManagementTeamMember(UserRequest userRequest) {
        User user = userMapper.toUser(userRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.MANAGEMENT_TEAM_MEMBER);
        user.setCreatedAt(Instant.now());
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }
}
