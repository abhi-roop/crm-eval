package com.cts.crm.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok annotation to generate getters and setters methods
@NoArgsConstructor // Generates a no-argument constructor for easy instantiation
@AllArgsConstructor // Generates a constructor with all fields as parameters
public class SalesAutomation {
    private Long opportunityId;
    private String salesStage;
    private BigDecimal estimatedValue;
    private LocalDateTime closingDate;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdatedDate;
}