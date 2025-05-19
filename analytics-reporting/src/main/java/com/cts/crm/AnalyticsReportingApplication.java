package com.cts.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication // Marks this class as a Spring Boot application
@EnableFeignClients // Enables Feign clients for making HTTP requests to external services
public class AnalyticsReportingApplication {

	public static void main(String[] args) {
		// Starts the Spring Boot application
		SpringApplication.run(AnalyticsReportingApplication.class, args);
	}
}
