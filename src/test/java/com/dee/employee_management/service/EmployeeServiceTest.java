package com.dee.employee_management.service;

import com.dee.employee_management.dto.EmployeeManagementResponse;
import com.dee.employee_management.dto.EmployeePayload;
import com.dee.employee_management.dto.registerEmployeeRequest;
import com.dee.employee_management.entity.employee;
import com.dee.employee_management.mapper.EmployeeMapper;
import com.dee.employee_management.repository.Employeerepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EmployeeServiceTest {

    @Mock
    private Employeerepository employeerepository;

    @Mock
    private EmployeeMapper employeeMapper;

    private Employeeservice employeeservice;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeservice = new Employeeservice(employeerepository, employeeMapper);
    }

    @Test
    void shouldSaveEmployee() {

        registerEmployeeRequest request = new registerEmployeeRequest();
        request.setName("John");
        request.setDepartment("IT");
        request.setEmail("john@test.com");

        employee entity = new employee();
        EmployeePayload payload = new EmployeePayload();

        when(employeeMapper.toEntity(request)).thenReturn(entity);
        when(employeerepository.save(entity)).thenReturn(entity);
        when(employeeMapper.toLoadResponse(entity)).thenReturn(payload);

        EmployeeManagementResponse<EmployeePayload> response =
                employeeservice.saveEmployee(request);

        assertEquals("00", response.getResponseCode());
        assertEquals("Succes", response.getMessage());
        assertNotNull(response.getPayload());

        verify(employeerepository).save(entity);
    }
}