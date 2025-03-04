//package com.demo.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Component
//public class TenantDataSourceConfig {
//
//    private final JdbcTemplate jdbcTemplate;
//    @Autowired
//    private  MultiTenantDataSource multiTenantDataSource; // Inject MultiTenantDataSource
//    private final Map<Object, Object> dataSources = new HashMap<>();
//
//    public TenantDataSourceConfig(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @PostConstruct
//    public void loadInitialTenants() {
//        List<Map<String, Object>> tenants = jdbcTemplate.queryForList("SELECT databaseName FROM institute");
//
//        for (Map<String, Object> tenant : tenants) {
//            String dbName = (String) tenant.get("databaseName");
//            dataSources.put(dbName, createDataSource(dbName));
//        }
//    }
//
//    public DataSource createDataSource(String dbName) {
//        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/" + dbName);
//        dataSource.setUsername("root");
//        dataSource.setPassword("Nikhil@0114");
//        return dataSource;
//    }
//
//    public void addNewTenant(String dbName) {
//        dataSources.put(dbName, createDataSource(dbName));
//        multiTenantDataSource.setTargetDataSources(dataSources);
//    }
//
//    public Map<Object, Object> getDataSources() {
//        return dataSources;
//    }
//}
//
//package com.demo.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Component
//public class TenantDataSourceConfig {
//    @Autowired
// private  MultiTenantDataSource multiTenantDataSource;
//    private final JdbcTemplate jdbcTemplate;
//    private final Map<Object, Object> dataSources = new HashMap<>();
//
//    public TenantDataSourceConfig(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @PostConstruct
//    public void loadInitialTenants() {
//        List<Map<String, Object>> tenants = jdbcTemplate.queryForList("SELECT databaseName FROM institute");
//
//        for (Map<String, Object> tenant : tenants) {
//            String dbName = (String) tenant.get("databaseName");
//            dataSources.put(dbName, createDataSource(dbName));
//        }
//    }
//
//    public DataSource createDataSource(String dbName) {
//        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/" + dbName);
//        dataSource.setUsername("root");
//        dataSource.setPassword("Nikhil@0114");
//        return dataSource;
//    }
//
//    public void addNewTenant(String dbName) {
//        dataSources.put(dbName, createDataSource(dbName));
//        multiTenantDataSource.setTargetDataSources(dataSources);
//        multiTenantDataSource.afterPropertiesSet(); // Refresh the target data sources
//    }
//
//
//    public Map<Object, Object> getDataSources() {
//        return dataSources;
//    }
//}


package com.demo.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TenantDataSourceConfig {


    private final JdbcTemplate jdbcTemplate;
    private final Map<String, DataSource> dataSources = new HashMap<>();
//    private final Map<Object, Object> dataSources = new HashMap<>();

    @Autowired
    private MultiTenantDataSource multiTenantDataSource; // ✅ Inject MultiTenantDataSource

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

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
// ✅ Update MultiTenantDataSource dynamically
//        multiTenantDataSource.setTargetDataSources(dataSources);
//        // Update MultiTenantDataSource with the initial tenants
        multiTenantDataSource.setTargetDataSources(new HashMap<>(dataSources));
        multiTenantDataSource.afterPropertiesSet();
    }

    public DataSource createDataSource(String dbName) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/" + dbName);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        dataSource.setMaximumPoolSize(10); // Set an appropriate pool size
        dataSource.setMinimumIdle(2);
        dataSource.setIdleTimeout(30000);
        dataSource.setMaxLifetime(1800000);
        return dataSource;
    }

    public void addNewTenant(String dbName) {
        if (dataSources.containsKey(dbName)) {
            ((HikariDataSource) dataSources.get(dbName)).close(); // Close old DataSource
        }

        DataSource newDataSource = createDataSource(dbName);
        dataSources.put(dbName, newDataSource);

        // Update MultiTenantDataSource dynamically
        multiTenantDataSource.setTargetDataSources(new HashMap<>(dataSources));
//        multiTenantDataSource.afterPropertiesSet(); // Refresh the data sources
    }

    public Map<String, DataSource> getDataSources() {
        return dataSources;
    }
//    public Map<Object, Object> getDataSources() {
//        return dataSources;
//    }
}
