package com.dee.employee_management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeManagementResponse<T> {
    private String responseCode;
    private String message;
    private T payload;
}
