package com.coditas.powerbridge.service.impl;

import com.coditas.powerbridge.dto.request.EmployeeRequest;
import com.coditas.powerbridge.dto.response.EmployeeResponse;
import com.coditas.powerbridge.entity.Employee;
import com.coditas.powerbridge.enums.Role;
import com.coditas.powerbridge.mapper.EmployeeMapper;
import com.coditas.powerbridge.repository.EmployeeRepository;
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

    private EmployeeResponse persistEmployee(EmployeeRequest request, Role role) {
        Employee employee = employeeMapper.toEmployee(request);
        employee.setRole(role);
        employee.setPassword(passwordEncoder.encode(request.getPassword()));
        employee.setCreatedAt(Instant.now());
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.toEmployeeResponse(savedEmployee);
    }
}
