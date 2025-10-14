package com.hustuni.schoolmanagement.entity;


import com.hustuni.schoolmanagement.entity.enumpk.Degree;
import com.hustuni.schoolmanagement.entity.enumpk.TeacherPosition;
import jakarta.persistence.*;

@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String teacherId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Degree degree;

    @Enumerated(EnumType.STRING)
    private TeacherPosition teacherPosition;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentclass_id")
    private StudentClass studentClass;
}
