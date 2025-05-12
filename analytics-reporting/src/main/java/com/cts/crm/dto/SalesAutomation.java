package com.cts.crm.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesAutomation {
    private Long opportunityId;
    private String salesStage;
    private BigDecimal estimatedValue;
    private LocalDateTime closingDate;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdatedDate;
}