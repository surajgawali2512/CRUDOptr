package com.demo.model;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Marks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int marksObtained;
    private int totalMarks;
    private String examType;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Subject subject;
}
