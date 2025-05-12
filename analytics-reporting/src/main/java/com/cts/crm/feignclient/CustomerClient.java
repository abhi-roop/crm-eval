package com.cts.crm.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.cts.crm.dto.CustomerData;


@FeignClient(name="CUSTOMER-DATA-MANAGEMENT",path="/api/customers")
public interface CustomerClient {
		@GetMapping
		public List<CustomerData> getAllCustomers();

}