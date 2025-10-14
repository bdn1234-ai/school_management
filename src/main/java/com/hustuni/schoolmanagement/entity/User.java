package com.hustuni.schoolmanagement.entity;


import com.hustuni.schoolmanagement.entity.enumpk.Gender;
import com.hustuni.schoolmanagement.entity.role.Role;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    private Gender gender;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private boolean enabled;

    @ManyToOne(fetch = FetchType.EAGER)
    private Department department;


    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

}
