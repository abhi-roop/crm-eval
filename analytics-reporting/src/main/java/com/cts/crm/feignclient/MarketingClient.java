package com.cts.crm.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.cts.crm.dto.MarketingAutomation;
@FeignClient(name="MARKETING-AUTOMATION",path="/api/marketing/campaigns")
public interface MarketingClient {
    @GetMapping
    public List<MarketingAutomation> getAllCampaigns();

}
