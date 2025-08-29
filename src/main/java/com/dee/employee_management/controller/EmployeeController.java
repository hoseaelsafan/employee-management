package com.dee.employee_management.controller;

import com.dee.employee_management.dto.EmployeeManagementResponse;
import com.dee.employee_management.dto.registerEmployeeRequest;
import com.dee.employee_management.entity.employee;
import com.dee.employee_management.service.EmployeeServiceInterface;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeServiceInterface employeeServiceInterface;

    public EmployeeController(EmployeeServiceInterface employeeServiceInterface) {
        this.employeeServiceInterface = employeeServiceInterface;
    }

    @PostMapping("/register")
    public ResponseEntity<EmployeeManagementResponse<employee>> register(@Valid @RequestBody registerEmployeeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeServiceInterface.saveEmployee(request));
    }

    @GetMapping
    public ResponseEntity<EmployeeManagementResponse<List<employee>>> getAll() {
        return ResponseEntity.ok(employeeServiceInterface.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeManagementResponse<employee>> getByID(@PathVariable Long id){
        //EmployeeManagementResponse<employee> response = employeeServiceInterface.getEmployeeById(id);
        return ResponseEntity.ok(employeeServiceInterface.getEmployeeById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeManagementResponse<employee>> updateById(@RequestBody registerEmployeeRequest request,@PathVariable Long id){
        EmployeeManagementResponse<employee> response = employeeServiceInterface.updateEmployee(request, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeManagementResponse<String>> delete(@PathVariable Long id){
        return ResponseEntity.ok(employeeServiceInterface.deleteEmployee(id));
    }

}
