package com.cts.crm.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.cts.crm.dto.SalesAutomation;


@FeignClient(name="SALES-AUTOMATION",path="/api/sales/opportunities")
public interface SalesClient {

	 @GetMapping
	    public List<SalesAutomation> getAllSales();
}
