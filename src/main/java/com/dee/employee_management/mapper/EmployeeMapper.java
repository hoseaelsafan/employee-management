package com.dee.employee_management.mapper;

import com.dee.employee_management.dto.EmployeeManagementResponse;
import com.dee.employee_management.dto.registerEmployeeRequest;
import com.dee.employee_management.entity.employee;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeMapper {

    public employee toEntity(registerEmployeeRequest dto){
        employee empl = new employee();
        empl.setName(dto.getName());
        empl.setDepartment(dto.getDepartment());
        empl.setEmail(dto.getEmail());
        return empl;
    }

    public EmployeeManagementResponse<employee> toResponse(employee entity) {
        return new EmployeeManagementResponse<>(
                "00", // success code
                "Employee saved successfully",
                entity
        );
    }

    public EmployeeManagementResponse<List<employee>> toResponseList(List<employee> entities) {
        return new EmployeeManagementResponse<>(
                "00",
                "Employees fetched successfully",
                entities
        );
    }
}
