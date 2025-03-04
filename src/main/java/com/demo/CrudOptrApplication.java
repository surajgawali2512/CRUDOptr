package com.demo;

import com.demo.filter.TenantFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudOptrApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudOptrApplication.class, args);
	}
	@Bean
	public FilterRegistrationBean<TenantFilter> tenantFilter() {
		FilterRegistrationBean<TenantFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new TenantFilter());
		registrationBean.addUrlPatterns("/api/*"); // Apply to API requests
		return registrationBean;
	}
}
