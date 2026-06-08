package com.coditas.powerbridge.common;

import com.coditas.powerbridge.dto.request.UserRequest;
import com.coditas.powerbridge.dto.response.UserResponse;
import com.coditas.powerbridge.entity.User;
import com.coditas.powerbridge.enums.Role;
import com.coditas.powerbridge.exception.ResourceAlreadyExistException;
import com.coditas.powerbridge.mapper.UserMapper;
import com.coditas.powerbridge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserPersistenceService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ServiceUtil serviceUtil;

    public UserResponse persistUser(UserRequest userRequest, Role role) {
        List<User> checkUser = userRepository.findAllByUsernameOrEmail(userRequest.getUsername(), userRequest.getEmail());

        boolean existByUsername = checkUser.stream()
                .anyMatch(u -> u.getUsername().equals(userRequest.getUsername()));

        boolean existByEmail = checkUser.stream()
                .anyMatch(u -> u.getEmail().equals(userRequest.getEmail()));

        if(existByUsername){
            throw new ResourceAlreadyExistException("Username already exists");
        }
        if(existByEmail){
            throw new ResourceAlreadyExistException("Email already exists");
        }

        User user = userMapper.toUser(userRequest);
        user.setPassword(serviceUtil.encodePassword(user));
        user.setRole(role);
        user.setCreatedAt(Instant.now());
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }
}
