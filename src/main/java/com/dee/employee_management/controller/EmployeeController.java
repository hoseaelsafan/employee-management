package com.dee.employee_management.controller;

import com.dee.employee_management.dto.EmployeeManagementResponse;
import com.dee.employee_management.dto.registerEmployeeRequest;
import com.dee.employee_management.entity.employee;
import com.dee.employee_management.service.Employeeservice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final Employeeservice employeeService;

    public EmployeeController(Employeeservice employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/register")
    public ResponseEntity<EmployeeManagementResponse<employee>> register(@RequestBody registerEmployeeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.saveEmployee(request));
    }

    @GetMapping
    public ResponseEntity<EmployeeManagementResponse<List<employee>>> getAll() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
}
