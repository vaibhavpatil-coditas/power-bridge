package com.coditas.powerbridge.service.impl;

import com.coditas.powerbridge.constants.ExceptionMessage;
import com.coditas.powerbridge.dto.request.StateHeadAssignmentRequest;
import com.coditas.powerbridge.dto.request.StateRequest;
import com.coditas.powerbridge.dto.response.StateResponse;
import com.coditas.powerbridge.entity.State;
import com.coditas.powerbridge.entity.User;
import com.coditas.powerbridge.enums.Role;
import com.coditas.powerbridge.exception.NotFoundException;
import com.coditas.powerbridge.exception.ResourceAlreadyExistException;
import com.coditas.powerbridge.exception.RoleMismatchedException;
import com.coditas.powerbridge.mapper.StateMapper;
import com.coditas.powerbridge.repository.StateRepository;
import com.coditas.powerbridge.repository.UserRepository;
import com.coditas.powerbridge.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class StateServiceImpl implements StateService {

    private final StateMapper stateMapper;
    private final StateRepository stateRepository;
    private final UserRepository userRepository;

    @Override
    public StateResponse create(StateRequest request) {
        if(stateRepository.existsByName(request.getName())){
            throw new ResourceAlreadyExistException(ExceptionMessage.STATE_ALREADY_ADDED);
        }
        State state = stateMapper.toState(request);
        state.setCreatedAt(Instant.now());
        State savedState = stateRepository.save(state);
        return stateMapper.toStateResponse(savedState);
    }

    @Override
    public StateResponse assignStateHead(Long stateId, StateHeadAssignmentRequest request) {
        State state = stateRepository.findById(stateId).orElseThrow(() -> new NotFoundException(ExceptionMessage.STATE_NOT_FOUND));
        User user = userRepository.findById(request.getId()).orElseThrow(() -> new NotFoundException(ExceptionMessage.USER_NOT_FOUND));
        if(!user.getRole().equals(Role.STATE_HEAD)){
            throw new RoleMismatchedException(ExceptionMessage.ROLE_MISMATCHED_STATE_HEAD);
        }
        state.setStateHead(user);
        State savedState = stateRepository.save(state);
        return stateMapper.toStateResponse(savedState);
    }
}
