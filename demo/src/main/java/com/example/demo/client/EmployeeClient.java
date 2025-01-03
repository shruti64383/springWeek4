package com.example.demo.client;

import com.example.demo.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeClient {
    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(Long employeeId);

    EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO);
}
