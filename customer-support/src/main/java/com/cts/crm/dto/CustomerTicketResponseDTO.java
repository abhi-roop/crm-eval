package com.cts.crm.dto;

import com.cts.crm.model.Tickets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerTicketResponseDTO {
 
	private Tickets ticket;
	private CustomerSupportDTO customerSupportDTO;
}
