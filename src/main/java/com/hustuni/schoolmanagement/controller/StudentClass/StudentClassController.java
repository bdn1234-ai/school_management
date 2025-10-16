package com.hustuni.schoolmanagement.controller.StudentClass;

import com.hustuni.schoolmanagement.service.StudentClass.StudentClassService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;


@Controller
@RequestMapping("/api/student-classes")
@AllArgsConstructor
public class StudentClassController {
    private final StudentClassService studentClassService;

    @GetMapping("/{id}")
    public String getStudentClass(@PathVariable Long id, Model model) {
        var classDetails = studentClassService.getStudentClass(id);
        model.addAttribute("classInfo", classDetails);
        return "class-details";
    }

}
