package com.demo.controller;

import com.demo.model.Institute;
import com.demo.repository.InstituteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/institutes")
public class InstituteController {

    @Autowired
    private InstituteRepository instituteRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping
    public String createInstitute(@RequestBody Institute institute) {
        // Save Institute in Master Database
        instituteRepository.save(institute);

        // Create a new database for the institute
        String sql = "CREATE DATABASE " + institute.getDatabaseName();
        jdbcTemplate.execute(sql);

        return "Institute created successfully!";
    }
}
