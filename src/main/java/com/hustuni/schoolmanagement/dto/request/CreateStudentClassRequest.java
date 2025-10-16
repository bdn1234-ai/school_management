package com.hustuni.schoolmanagement.dto.request;

import com.hustuni.schoolmanagement.entity.Department;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class CreateStudentClassRequest {
    @NotBlank(message = "Class name cannot be blank")
    private String className;

    private String description;

    @NotNull(message = "Department ID cannot be null")
    private Long departmentID;
}
