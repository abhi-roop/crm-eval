package com.cts.crm.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok annotation to generate getters, setters and toString methods
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields as arguments
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
public class CustomerSupport {

    // Unique identifier for the support ticket
    private Long ticketId;

    // Description of the issue reported by the customer
    private String issueDescription;

    // Name of the support agent assigned to handle the ticket
    private String assignedAgent;

    // Status of the ticket (e.g., Open, In Progress, Resolved)
    private String status;

    // Subject of the support ticket
    private String subject;

    // Detailed description of the customer's issue
    private String description;

    // Date and time when the ticket was created
    private LocalDateTime creationDate;

    // Date and time when the ticket was last updated
    private LocalDateTime lastUpdatedDate;
}
