package com.hustuni.schoolmanagement.service.StudentClass.impl;

import com.hustuni.schoolmanagement.dto.request.CreateStudentClassRequest;
import com.hustuni.schoolmanagement.dto.response.GetStudentClass.GetStudentClassResponse;
import com.hustuni.schoolmanagement.dto.response.GetStudentClass.InfoDTO.DepartmentInfoDTO;
import com.hustuni.schoolmanagement.dto.response.GetStudentClass.InfoDTO.StudentInfoDTO;
import com.hustuni.schoolmanagement.dto.response.GetStudentClass.InfoDTO.TeacherInfoDTO;
import com.hustuni.schoolmanagement.entity.Department;
import com.hustuni.schoolmanagement.entity.Student;
import com.hustuni.schoolmanagement.entity.StudentClass;
import com.hustuni.schoolmanagement.entity.Teacher;
import com.hustuni.schoolmanagement.exception.NotFoundException;
import com.hustuni.schoolmanagement.repository.DepartmentRepository;
import com.hustuni.schoolmanagement.repository.StudentClassRepository;
import com.hustuni.schoolmanagement.service.StudentClass.StudentClassService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class StudentClassServiceImpl implements StudentClassService {
    private final StudentClassRepository studentClassRepository;

    private final DepartmentRepository departmentRepository;

    @Override
    public void createStudentClass(CreateStudentClassRequest request) {
        // 1. Dùng departmentId từ request để tìm Department entity trong DB
        Department department = departmentRepository.findById(request.getDepartmentID())
                .orElseThrow(() -> new NotFoundException("Department not found with id: " + request.getDepartmentID()));

        // 2. Tạo một đối tượng StudentClass mới
        StudentClass newStudentClass = new StudentClass();
        newStudentClass.setClassName(request.getClassName());
        newStudentClass.setDescription(request.getDescription());

        // 3. Set đối tượng Department vừa tìm được vào lớp học mới
        newStudentClass.setDepartment(department); // Giả sử bạn có quan hệ này

        // 4. Lưu lớp học mới vào DB
        studentClassRepository.save(newStudentClass);
    }

    @Override
    public GetStudentClassResponse getStudentClass(Long id){

        StudentClass studentClass = studentClassRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student class not found with id: " + id));
        GetStudentClassResponse response = new GetStudentClassResponse();
        //phải match từng trường vào dto
        //có thể dùng thư viện nhưng do mình ko bt config nên tạm ae dùng như thế này nhé:)))
        response.setClassName(studentClass.getClassName());
        response.setDescription(studentClass.getDescription());

        response.setDepartment(new DepartmentInfoDTO(
                studentClass.getDepartment().getId(),
                studentClass.getDepartment().getDepartmentName()
        ));

        response.setClassPresident(
                studentClass.getClassPresident() == null ? null :
                new StudentInfoDTO(
                        studentClass.getClassPresident().getId(),
                        studentClass.getClassPresident().getUser().getFullName()
                )
        );

        response.setClassVicePresident(
                studentClass.getClassVicePresident() == null ? null :
                new StudentInfoDTO(
                        studentClass.getClassVicePresident().getId(),
                        studentClass.getClassVicePresident().getUser().getFullName()
                )
        );

        response.setClassSecretary(
                studentClass.getClassSecretary() == null ? null :
                new StudentInfoDTO(
                        studentClass.getClassSecretary().getId(),
                        studentClass.getClassSecretary().getUser().getFullName()
                )
        );

        if (studentClass.getStudents() != null) {
            // Dùng Stream API để chuyển đổi list
            List<StudentInfoDTO> studentDTOs = studentClass.getStudents()
                    .stream()
                    .map(this::mapStudentToInfoDTO)
                    .toList();
            // Set danh sách DTO đã được chuyển đổi vào response
            response.setStudents(studentDTOs);
        }

        if (studentClass.getTeachers() != null) {
            List<TeacherInfoDTO> teacherDTOs = studentClass.getTeachers()
                    .stream()
                    .map(this::mapTeacherToInfoDTO)
                    .toList();
            response.setTeachers(teacherDTOs);
        }

        return response;
    }


    private StudentInfoDTO mapStudentToInfoDTO(Student student) {
        if (student == null) {
            return null;
        }
        return new StudentInfoDTO(student.getId(), student.getUser().getFullName());
    }
    private TeacherInfoDTO mapTeacherToInfoDTO(Teacher teacher){
        if (teacher == null){
            return null;
        }
        return new TeacherInfoDTO(teacher.getId(), teacher.getUser().getFullName());
    }
}


