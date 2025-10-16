package com.hustuni.schoolmanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "student_classes")
@Getter
@Setter
public class StudentClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String className;
    
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "studentClass")
    private List<Teacher> teachers;

    @OneToMany(mappedBy = "studentClass")
    private List<Student> students;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_president_id", unique = true)
    private Student classPresident;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_vice_president_id", unique = true)
    private Student classVicePresident;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_secretary_id", unique = true)
    private Student classSecretary;

}
