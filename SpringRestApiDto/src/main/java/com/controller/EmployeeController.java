package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Dto.EmployeeDto;
import com.exception.ResourceNotFoundException;
import com.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/fetch/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable Long employeeId) throws ResourceNotFoundException {
        EmployeeDto employeeDto = employeeService.getEmployee(employeeId);
        return ResponseEntity.ok(employeeDto);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @PutMapping("/update/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long employeeId, @RequestBody @Valid EmployeeDto employeeDto) throws ResourceNotFoundException {
        EmployeeDto updatedEmployee = employeeService.updateEmployee(employeeId, employeeDto);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/remove/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long employeeId) throws ResourceNotFoundException {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.noContent().build();
    }
}
