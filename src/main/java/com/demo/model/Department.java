package com.demo.model;
import  jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Institution institution;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Course> courses;
}
