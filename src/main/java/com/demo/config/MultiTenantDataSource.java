package com.demo.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
public class MultiTenantDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> currentTenant = new ThreadLocal<>();
    private final Map<Object, Object> targetDataSources = new HashMap<>();
    private final DataSource defaultDataSource;

    public MultiTenantDataSource(DataSource defaultDataSource) {
        this.defaultDataSource = defaultDataSource;
        setDefaultTargetDataSource(defaultDataSource); // Set default DB
        setTargetDataSources(targetDataSources);
        afterPropertiesSet();
    }

    public static void setCurrentTenant(String tenant) {
        currentTenant.set(tenant);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return currentTenant.get();
    }

    public void addNewTenant(String tenantId, DataSource dataSource) {
        targetDataSources.put(tenantId, dataSource);
        setTargetDataSources(targetDataSources); // Update available tenants
        afterPropertiesSet(); // Refresh DataSource
    }
}
