package com.demo.model;

import jakarta.persistence.*;

import lombok.*;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class College {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private  int collegeId;

    private  String collegeName;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "college", cascade = CascadeType.ALL)
    private List<Branch> branch;
}

