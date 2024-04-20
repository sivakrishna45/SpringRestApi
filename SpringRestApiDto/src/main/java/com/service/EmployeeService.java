package com.service;

import java.util.List;

import com.Dto.EmployeeDto;
import com.exception.ResourceNotFoundException;

public interface EmployeeService {
    
    public EmployeeDto saveEmployee(EmployeeDto employeeDto);
    
    public List<EmployeeDto> getAllEmployees();
    
    public EmployeeDto getEmployee(Long employeeId) throws ResourceNotFoundException;
    
    public void deleteEmployee(Long employeeId)throws ResourceNotFoundException;
    
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto) throws ResourceNotFoundException;
}
