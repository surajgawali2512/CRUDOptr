package com.demo.service;

import com.demo.config.TenantDataSourceConfig;
import com.demo.model.Institute;
import com.demo.repository.InstituteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class InstituteService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private InstituteRepository instituteRepository;

    @Autowired
    private TenantDataSourceConfig tenantDataSourceConfig;

    @Transactional
    public Institute createInstitute(Institute institute) {
        // Save institute details
        Institute savedInstitute = instituteRepository.save(institute);

        // Create new database dynamically
        String createDbSQL = "CREATE DATABASE " + institute.getDatabaseName();
        jdbcTemplate.execute(createDbSQL);

        // Apply schema to the new database
        String useDbSQL = "USE " + institute.getDatabaseName();
        jdbcTemplate.execute(useDbSQL);

        String applySchemaSQL = "CREATE TABLE students (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), email VARCHAR(255))";
        jdbcTemplate.execute(applySchemaSQL);

        // Register new database in the DataSource
        tenantDataSourceConfig.addNewTenant(institute.getDatabaseName());

        return savedInstitute;
    }
}
