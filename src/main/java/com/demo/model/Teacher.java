package com.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String teacherId;
    private String subject;
    private String mobNo;
    private String email;
    private String qualification;
    private int experience;
    private String gender;

    @Embedded
    private Address address;
}

