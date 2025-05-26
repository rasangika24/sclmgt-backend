package com.bit.backend.services;

import com.bit.backend.dtos.EmployeeDto;

import java.util.List;
import java.util.Map;

public interface EmployeeServiceI {

    EmployeeDto addEmployeeEntity(EmployeeDto employeeDto);

    List<EmployeeDto> getEmployee();

    EmployeeDto updateEmployee(long id, EmployeeDto employeeDto);

    EmployeeDto deleteEmployee(long id);

    List<Map<String, Object>> getTeachers();
}
