package com.cts.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AnalyticsReportingApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnalyticsReportingApplication.class, args);
    }
}