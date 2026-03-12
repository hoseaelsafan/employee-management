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

import java.util.List;
import java.util.Optional;

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

    @Test
    void shouldReturnAllEmployees() {

        List<employee> employees = List.of(
                new employee(),
                new employee()
        );

        List<EmployeePayload> payloads = List.of(
                new EmployeePayload(),
                new EmployeePayload()
        );

        when(employeerepository.findAll()).thenReturn(employees);
        when(employeeMapper.toListLoadResponse(employees)).thenReturn(payloads);

        EmployeeManagementResponse<List<EmployeePayload>> response =
                employeeservice.getAllEmployees();

        assertEquals("00", response.getResponseCode());
        assertEquals("Succes", response.getMessage());
        assertNotNull(response.getPayload());
        assertEquals(2, response.getPayload().size());

        verify(employeerepository).findAll();
    }

    @Test
    void shouldGetEmployeeById() {

        Long id = 1L;

        employee entity = new employee();
        EmployeePayload payload = new EmployeePayload();

        when(employeerepository.findById(id)).thenReturn(Optional.of(entity));
        when(employeeMapper.toLoadResponse(entity)).thenReturn(payload);

        EmployeeManagementResponse<EmployeePayload> response =
                employeeservice.getEmployeeById(id);

        assertEquals("00", response.getResponseCode());
        assertEquals("Succes", response.getMessage());
        assertNotNull(response.getPayload());

        verify(employeerepository).findById(id);
    }

    @Test
    void shouldUpdateEmployee() {

        Long id = 1L;

        registerEmployeeRequest request = new registerEmployeeRequest();
        request.setName("Updated");
        request.setDepartment("HR");
        request.setEmail("updated@test.com");

        employee existing = new employee();
        EmployeePayload payload = new EmployeePayload();

        when(employeerepository.findById(id)).thenReturn(Optional.of(existing));
        when(employeerepository.save(existing)).thenReturn(existing);
        when(employeeMapper.toLoadResponse(existing)).thenReturn(payload);

        EmployeeManagementResponse<EmployeePayload> response =
                employeeservice.updateEmployee(request, id);

        assertEquals("00", response.getResponseCode());
        assertNotNull(response.getPayload());

        verify(employeerepository).save(existing);
    }

    @Test
    void shouldDeleteEmployee() {

        Long id = 1L;

        when(employeerepository.existsById(id)).thenReturn(true);

        EmployeeManagementResponse<String> response =
                employeeservice.deleteEmployee(id);

        assertEquals("00", response.getResponseCode());

        verify(employeerepository).deleteById(id);
    }

}