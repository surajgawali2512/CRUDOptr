package com.demo.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TenantDataSourceConfig {
    @Autowired
   private DataSourceConfig dataSourceConfig;
    private final JdbcTemplate jdbcTemplate;
    private final MultiTenantDataSource multiTenantDataSource;
    private final Map<Object, Object> dataSources = new HashMap<>();

    public TenantDataSourceConfig(@Lazy JdbcTemplate jdbcTemplate, MultiTenantDataSource multiTenantDataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.multiTenantDataSource = multiTenantDataSource;
    }

    @PostConstruct
    public void loadInitialTenants() {
        List<Map<String, Object>> tenants = jdbcTemplate.queryForList("SELECT databaseName FROM college");

        for (Map<String, Object> tenant : tenants) {
            String dbName = (String) tenant.get("databaseName");
            DataSource dataSource = createDataSource(dbName);
            dataSources.put(dbName, dataSource);
            multiTenantDataSource.addNewTenant(dbName, dataSource);
        }
    }

    public DataSource createDataSource(String dbName) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/" + dbName);
        dataSource.setUsername("root");
        dataSource.setPassword("Suraj@2002");
        return dataSource;
    }
}
