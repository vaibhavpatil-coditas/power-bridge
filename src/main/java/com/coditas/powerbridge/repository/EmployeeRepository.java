package com.coditas.powerbridge.repository;

import com.coditas.powerbridge.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmail(String identity);
}
