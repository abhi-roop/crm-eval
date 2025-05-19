package com.cts.crm.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok annotation to generate getters and setters
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields as arguments
public class CustomerAnalytics {

	// Holds customer-related data
	private CustomerData customerData;

	// List containing customer support tickets related to the customer
	private List<CustomerSupport> ticketData;
}
