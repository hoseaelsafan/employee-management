package com.dee.employee_management.service;

import com.dee.employee_management.dto.EmployeeManagementResponse;
import com.dee.employee_management.dto.registerEmployeeRequest;
import com.dee.employee_management.entity.employee;
import com.dee.employee_management.mapper.EmployeeMapper;
import com.dee.employee_management.repository.Employeerepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Employeeservice {

    private final Employeerepository employeerepository;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public Employeeservice(Employeerepository employeerepository, EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
        this.employeerepository = employeerepository;
    }

    public EmployeeManagementResponse<employee> saveEmployee(registerEmployeeRequest request) {
        employee entity = employeeMapper.toEntity(request);
        employee saved = employeerepository.save(entity);
        return employeeMapper.toResponse(saved);
    }

    public EmployeeManagementResponse<List<employee>> getAllEmployees() {
        List<employee> list = employeerepository.findAll();
        return employeeMapper.toResponseList(list);
    }


}
