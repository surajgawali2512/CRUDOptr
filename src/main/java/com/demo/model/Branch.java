package com.demo.model;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
class  Branch{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int Id;
    private String branchCode;
    private String branchName;
    @ManyToOne
 @JoinColumn(name = "college" ,referencedColumnName = "collegeId")
    private  College college;

}
