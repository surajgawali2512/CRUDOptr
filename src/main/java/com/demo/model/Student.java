package com.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String rollNo;
    private String mobNo;
    private String email;
    private String std;
    private String aadharNo;
    private String gender;

    @Embedded
    private Address address;
}
