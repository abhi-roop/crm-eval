package com.cts.crm.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerTicketResponse {
    private List<CustomerAnalytics> customerAnalyticsList;
    private List<SalesAutomation> salesAnalyticsList;
    private List<MarketingAutomation> marketingAnalyticsList;
}