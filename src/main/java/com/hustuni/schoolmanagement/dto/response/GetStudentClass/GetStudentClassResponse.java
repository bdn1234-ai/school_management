package com.hustuni.schoolmanagement.dto.response.GetStudentClass;

import com.hustuni.schoolmanagement.dto.response.GetStudentClass.InfoDTO.DepartmentInfoDTO;
import com.hustuni.schoolmanagement.dto.response.GetStudentClass.InfoDTO.StudentInfoDTO;
import com.hustuni.schoolmanagement.dto.response.GetStudentClass.InfoDTO.TeacherInfoDTO;
import com.hustuni.schoolmanagement.entity.Department;
import com.hustuni.schoolmanagement.entity.Student;
import com.hustuni.schoolmanagement.entity.Teacher;
import lombok.Data;

import java.util.List;

@Data
public class GetStudentClassResponse {
    private String className;

    private String description;

    private DepartmentInfoDTO department;

    private List<TeacherInfoDTO> teachers;

    private List<StudentInfoDTO> students;

    private StudentInfoDTO classPresident;

    private StudentInfoDTO classVicePresident;

    private StudentInfoDTO classSecretary;
}
