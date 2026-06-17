package com.coditas.powerbridge.common;

import com.coditas.powerbridge.entity.Employee;
import com.coditas.powerbridge.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
public class TenantSwitchUtil {

    private final EmployeeRepository employeeRepository;

    public void saveEmployee(Employee employee){
        employeeRepository.save(employee);
    }
}
