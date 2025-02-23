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
    private Long marksId;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Subject subject;

    private int marksObtained;
    private int totalMarks;
    private String examType;
}
