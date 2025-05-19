package com.cts.crm.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok annotation to generate getters and setters methods
@NoArgsConstructor // Generates a no-argument constructor for easy instantiation
@AllArgsConstructor // Generates a constructor with all fields as parameters
public class CustomerTicketResponse {

	// List containing customer analytics data
	private List<CustomerAnalytics> customerAnalyticsList;

	// List containing sales automation analytics data
	private List<SalesAutomation> salesAnalyticsList;

	// List containing marketing automation analytics data
	private List<MarketingAutomation> marketingAnalyticsList;
}
