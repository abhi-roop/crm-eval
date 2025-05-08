package com.cts.crm.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.crm.dto.CustomerSupportDTO;


@FeignClient(name="CUSTOMERDATAMANAGEMENT" ,url="http://localhost:8081/api/customers")
public interface CustomerClient {
    @GetMapping("/{id}")
    public CustomerSupportDTO getCustomerById(@PathVariable ("id") Long id);
    
    

}
