package com.demo.config;



import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;

@Component
public class MultiTenantDataSource extends AbstractRoutingDataSource {

    private static Map<Object, Object> targetDataSources;

        public void setTargetDataSources(Map<Object, Object> dataSources) {
            super.setTargetDataSources(dataSources);
            afterPropertiesSet(); // Ensure the new data sources are registered
        }


    @Override
    protected Object determineCurrentLookupKey() {
        return TenantContext.getCurrentTenant();
    }

    @Override
    public void afterPropertiesSet() {
        if (targetDataSources != null) {
            setTargetDataSources(targetDataSources);
            super.afterPropertiesSet();
        }
    }
}
