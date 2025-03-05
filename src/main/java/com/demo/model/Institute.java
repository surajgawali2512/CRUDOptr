package com.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Institute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private String name;
    private String address;
    private String email;
    private String phone;
private String password;
private  String databaseName;
//    @OneToMany(mappedBy = "institution", cascade = CascadeType.ALL)
//    private List<Department> departments;

    public String getDatabaseName() {
        return databaseName;
    }
}
