package com.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  // Fixed casing of 'Id' to 'id'

    private String branchCode;
    private String branchName;

    @ManyToOne
    @JoinColumn(name = "college_id", referencedColumnName = "collegeId", nullable = false)
    private College college;
}
