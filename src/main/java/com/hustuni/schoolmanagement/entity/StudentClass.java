package com.hustuni.schoolmanagement.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "student_classes")
public class StudentClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String className;
    
    private String description;

    @OneToMany(mappedBy = "studentClass")
    private List<Teacher> teachers;

    @OneToMany(mappedBy = "studentClass")
    private List<Student> students;
}
