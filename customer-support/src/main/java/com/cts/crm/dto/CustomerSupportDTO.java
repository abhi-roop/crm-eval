package com.cts.crm.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSupportDTO {

	 private Long customerId;
	  private String name;
	    private String email;
	    private String phone;
	    private List<String> purchaseHistory;
}
