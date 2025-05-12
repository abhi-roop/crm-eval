package com.cts.crm.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.crm.dto.CustomerSupport;
@FeignClient(name="CUSTOMER-SUPPORT",path="/api/support/tickets")
public interface SupportClient {
	
	@GetMapping("/customer/{customerId}")
	public List<CustomerSupport> getTicketsByCustomerId(@PathVariable Long customerId);
}
