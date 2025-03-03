package com.demo.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TenantDataSourceConfig {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    private  MultiTenantDataSource multiTenantDataSource; // Inject MultiTenantDataSource
    private final Map<Object, Object> dataSources = new HashMap<>();

    public TenantDataSourceConfig(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void loadInitialTenants() {
        List<Map<String, Object>> tenants = jdbcTemplate.queryForList("SELECT databaseName FROM institute");

        for (Map<String, Object> tenant : tenants) {
            String dbName = (String) tenant.get("databaseName");
            dataSources.put(dbName, createDataSource(dbName));
        }
    }

    public DataSource createDataSource(String dbName) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/" + dbName);
        dataSource.setUsername("root");
        dataSource.setPassword("Nikhil@0114");
        return dataSource;
    }

    public void addNewTenant(String dbName) {
        dataSources.put(dbName, createDataSource(dbName));
        multiTenantDataSource.setTargetDataSources(dataSources);
    }

    public Map<Object, Object> getDataSources() {
        return dataSources;
    }
}
