package com.hustuni.schoolmanagement.service.StudentClass;

import com.hustuni.schoolmanagement.dto.request.CreateStudentClassRequest;
import com.hustuni.schoolmanagement.dto.response.GetStudentClass.GetStudentClassResponse;

public interface StudentClassService {
    void createStudentClass(CreateStudentClassRequest request);
    GetStudentClassResponse getStudentClass(Long id);
}
