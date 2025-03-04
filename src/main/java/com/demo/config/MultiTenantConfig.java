package com.demo.config;

import com.demo.model.Institute;
import com.demo.repository.InstituteRepository;
import com.demo.tenant.MultiTenantDataSource;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class MultiTenantConfig {

    @Autowired
    private InstituteRepository instituteRepository;

    @Value("${spring.datasource.url}") // Master DB
    private String masterUrl;

    @Value("${spring.datasource.username}")
    private String masterUsername;

    @Value("${spring.datasource.password}")
    private String masterPassword;

    private final Map<Object, Object> dataSources = new HashMap<>();

    @PostConstruct
    public void loadInstitutes() {
        List<Institute> institutes = instituteRepository.findAll();
        for (Institute institute : institutes) {
            dataSources.put(institute.getDatabaseName(), createDataSource(institute.getDatabaseName()));
        }
    }

    private DataSource createDataSource(String dbName) {
        return DataSourceBuilder.create()
                .url(masterUrl.replace("master_db", dbName)) // Replace with actual DB
                .username(masterUsername)
                .password(masterPassword)
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }

    @Bean
    public DataSource dataSource() {
        MultiTenantDataSource multiTenantDataSource = new MultiTenantDataSource();
        multiTenantDataSource.setDefaultTargetDataSource(createMasterDataSource());
        multiTenantDataSource.setTargetDataSources(dataSources);
        multiTenantDataSource.afterPropertiesSet();
        return multiTenantDataSource;
    }

    private DataSource createMasterDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(masterUrl);
        dataSource.setUsername(masterUsername);
        dataSource.setPassword(masterPassword);
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return dataSource;
    }
}
