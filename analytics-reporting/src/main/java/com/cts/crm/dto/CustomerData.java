package com.cts.crm.dto;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok annotation to generate boilerplate code like getters and setters
@AllArgsConstructor // Generates a constructor with all fields as parameters
@NoArgsConstructor // Generates a no-argument constructor for easy object instantiation
public class CustomerData {

	// Unique identifier for the customer
	private Long customerId;

	// Name of the customer
	private String name;

	// Email address for customer communication
	private String email;

	// Contact number of the customer
	private String phone;

	// Physical address of the customer
	private String address;

	// List of items purchased by the customer
	private List<String> purchaseHistory;

	// Segmentation data with key-value pairs for targeted marketing
	private Map<String, String> segmentationData;

	// Geographic region associated with the customer
	private String region;

	// Customer's interests for personalized recommendations
	private String interests;

	// Customer’s purchasing habits, useful for behavior analysis
	private String purchasingHabits;
}
