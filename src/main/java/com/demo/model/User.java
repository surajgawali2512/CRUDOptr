package com.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Fixed AUTO to IDENTITY for MySQL
    private Long id;

    private String name;
    private String email;
}
