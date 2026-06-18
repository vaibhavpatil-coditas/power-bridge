package com.coditas.powerbridge.service.impl;

import com.coditas.powerbridge.constants.ExceptionMessage;
import com.coditas.powerbridge.dto.request.BPOStateRequest;
import com.coditas.powerbridge.dto.request.EmployeeRequest;
import com.coditas.powerbridge.dto.response.BPOStateResponse;
import com.coditas.powerbridge.dto.response.EmployeeResponse;
import com.coditas.powerbridge.entity.BPOState;
import com.coditas.powerbridge.entity.BPOStateId;
import com.coditas.powerbridge.entity.Employee;
import com.coditas.powerbridge.entity.State;
import com.coditas.powerbridge.enums.Role;
import com.coditas.powerbridge.exception.NotFoundException;
import com.coditas.powerbridge.mapper.BPOStateMapper;
import com.coditas.powerbridge.mapper.EmployeeMapper;
import com.coditas.powerbridge.repository.BpoStateRepository;
import com.coditas.powerbridge.repository.EmployeeRepository;
import com.coditas.powerbridge.repository.StateRepository;
import com.coditas.powerbridge.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final BPOStateMapper bpoStateMapper;
    private final BpoStateRepository bpoStateRepository;
    private final StateRepository stateRepository;

    @Override
    public EmployeeResponse onboardSalesTeamMember(EmployeeRequest request) {
        return persistEmployee(request, Role.SALES_TEAM_MEMBER);
    }

    @Override
    public EmployeeResponse onboardOperationsHead(EmployeeRequest request) {
        return persistEmployee(request, Role.OPERATIONS_HEAD);
    }

    @Override
    public EmployeeResponse onboardBPO(EmployeeRequest request) {
        return persistEmployee(request, Role.BPO);
    }

    @Override
    public BPOStateResponse assignStateToBPO(Long bpoId, BPOStateRequest request) {
        Employee bpo = employeeRepository.findById(bpoId).orElseThrow(()->
                new NotFoundException(ExceptionMessage.EMPLOYEE_NOT_FOUND));

        State state = stateRepository.findById(request.getStateID()).orElseThrow(()->
                new NotFoundException(ExceptionMessage.STATE_NOT_FOUND));

        BPOState bpoState = BPOState.builder()
                .id(new BPOStateId(bpoId, request.getStateID()))
                .bpo(bpo)
                .state(state)
                .build();

        BPOState savedBpoState = bpoStateRepository.save(bpoState);
        return bpoStateMapper.toBPOStateResponse(savedBpoState);
    }

    private EmployeeResponse persistEmployee(EmployeeRequest request, Role role) {
        Employee employee = employeeMapper.toEmployee(request);
        employee.setRole(role);
        employee.setPassword(passwordEncoder.encode(request.getPassword()));
        employee.setCreatedAt(Instant.now());
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.toEmployeeResponse(savedEmployee);
    }
}
