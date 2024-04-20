package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Dto.EmployeeDto;
import com.entity.Employee;
import com.exception.ResourceNotFoundException;
import com.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
	    Employee employee = employeeDtoToEntity(employeeDto);
	    Employee savedEmployee = employeeRepository.save(employee);
	    return entityToEmployeeDto(savedEmployee);
	}

	private EmployeeDto entityToEmployeeDto(Employee savedEmployee) {
	    EmployeeDto employeeDto = new EmployeeDto();
	    employeeDto.setEmployeeId(savedEmployee.getEmployeeId());
	    employeeDto.setFirstName(savedEmployee.getFirstName());
	    employeeDto.setLastName(savedEmployee.getLastName());
	    employeeDto.setDepartment(savedEmployee.getDepartment());
	    employeeDto.setMobileNumber(savedEmployee.getMobileNumber());
	    employeeDto.setSalary(savedEmployee.getSalary());
	    return employeeDto;
	}

	private Employee employeeDtoToEntity(EmployeeDto employeeDto) {
	    Employee employee = new Employee();
	    employee.setFirstName(employeeDto.getFirstName());
	    employee.setLastName(employeeDto.getLastName());
	    employee.setDepartment(employeeDto.getDepartment());
	    employee.setMobileNumber(employeeDto.getMobileNumber());
	    employee.setSalary(employeeDto.getSalary());
	    return employee;
	}




	@Override
	public void deleteEmployee(Long employeeId) throws ResourceNotFoundException {
	    Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
	    if (optionalEmployee.isPresent()) {
	        employeeRepository.deleteById(employeeId);
	    } else {
	        throw new ResourceNotFoundException("Employee does not exist with the given id: " + employeeId);
	    }
	}


	@Override
	public EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto) throws ResourceNotFoundException {
	    Employee existingEmployee = employeeRepository.findById(employeeId)
	            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

	    existingEmployee.setSalary(50000);
	    
	    Employee updatedEmployee = employeeRepository.save(existingEmployee);

	    return entityToEmployeeDto(updatedEmployee);
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
	    List<Employee> employees = employeeRepository.findAll();
	    List<EmployeeDto> employeeDtos = new ArrayList<>();
	    for (Employee employee : employees) {
	        employeeDtos.add(entityToEmployeeDto(employee));
	    }
	    return employeeDtos;
	}


	@Override
	public EmployeeDto getEmployee(Long employeeId) throws ResourceNotFoundException {
	    Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
	    if (optionalEmployee.isPresent()) {
	        return entityToEmployeeDto(optionalEmployee.get());
	    } else {
	        throw new ResourceNotFoundException("Employee is not exists with the given id "+employeeId);
	    }
	}
	

}
