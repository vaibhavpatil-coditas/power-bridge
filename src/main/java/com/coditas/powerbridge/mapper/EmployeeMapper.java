package com.coditas.powerbridge.mapper;

import com.coditas.powerbridge.dto.request.EmployeeRequest;
import com.coditas.powerbridge.dto.response.EmployeeResponse;
import com.coditas.powerbridge.entity.Employee;
import jakarta.validation.constraints.NotBlank;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee toEmployee(@NotBlank EmployeeRequest employeeRequest);

    EmployeeResponse toEmployeeResponse(Employee savedEmployee);
}
