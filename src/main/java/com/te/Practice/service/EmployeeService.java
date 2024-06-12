package com.te.Practice.service;

import com.te.Practice.dto.EmployeeDTO;
import com.te.Practice.dto.EmployeeRegDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeRegDTO saveEmployees(EmployeeRegDTO employeeRegDTO);

    EmployeeRegDTO getEmployee(Integer employeeId);

    List<EmployeeRegDTO> getAllEmployees();

    EmployeeDTO updateEmployee(Integer employeeId, Integer age, String empName);

    Integer deleteEmployee(Integer employeeId);
}
