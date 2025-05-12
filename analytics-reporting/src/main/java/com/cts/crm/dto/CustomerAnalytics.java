package com.cts.crm.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAnalytics {
    private CustomerData customerData;
    private List<CustomerSupport> ticketData;
}