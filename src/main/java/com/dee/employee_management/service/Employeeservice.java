package com.dee.employee_management.service;

import com.dee.employee_management.dto.EmployeeManagementResponse;
import com.dee.employee_management.dto.registerEmployeeRequest;
import com.dee.employee_management.entity.employee;
import com.dee.employee_management.mapper.EmployeeMapper;
import com.dee.employee_management.repository.Employeerepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//this class for service implementation(logic)
@Service
public class Employeeservice implements EmployeeServiceInterface{

    private final Employeerepository employeerepository;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public Employeeservice(Employeerepository employeerepository, EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
        this.employeerepository = employeerepository;
    }

    @Override
    public EmployeeManagementResponse<employee> saveEmployee(registerEmployeeRequest request) {
        employee entity = employeeMapper.toEntity(request);
        employee saved = employeerepository.save(entity);
        return employeeMapper.toResponse(saved);
    }

    @Override
    public EmployeeManagementResponse<List<employee>> getAllEmployees() {
        List<employee> list = employeerepository.findAll();
        return employeeMapper.toResponseList(list);
    }

    @Override
    public EmployeeManagementResponse<employee> getEmployeeById(Long id) {
        Optional<employee> optional = employeerepository.findById(id);
        return optional.map(employeeMapper::toResponse)
                .orElseGet(() -> new EmployeeManagementResponse<>("01", "Employee not found", null));
    }

    @Override
    public EmployeeManagementResponse<employee> updateEmployee(registerEmployeeRequest request, Long id){
        Optional<employee> optional = employeerepository.findById(id);

        if (optional.isEmpty()) {
            return new EmployeeManagementResponse<>("01", "Employee not found", null);
        }

        // 1. Get the existing employee
        employee existing = optional.get();

        // 2. Update fields manually
        existing.setName(request.getName());
        existing.setDepartment(request.getDepartment());
        existing.setEmail(request.getEmail());

        // 3. Save the updated employee
        employee updated = employeerepository.save(existing);

        // 4. Return response
        return employeeMapper.toResponse(updated);
    }

    @Override
    public EmployeeManagementResponse<String> deleteEmployee(Long id) {
        if (!employeerepository.existsById(id)) {
            return new EmployeeManagementResponse<>("01", "Employee not found", null);
        }
        employeerepository.deleteById(id);
        return new EmployeeManagementResponse<>("00", "Employee deleted successfully", "Deleted ID: " + id);
    }
}
