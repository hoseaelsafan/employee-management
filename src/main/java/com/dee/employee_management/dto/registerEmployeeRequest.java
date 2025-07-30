package com.dee.employee_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class registerEmployeeRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String department;

    @NotBlank
    @Email
    private String email;
}
