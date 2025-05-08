package com.cts.crm.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSupport {
	private Long ticketId;
	private String issueDescription;
	private String assignedAgent;
	private String status;
	private String subject;
	private String description;
	private LocalDateTime creationDate;
	private LocalDateTime lastUpdatedDate;

}
