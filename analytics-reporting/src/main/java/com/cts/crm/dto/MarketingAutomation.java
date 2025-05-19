package com.cts.crm.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok annotation to generate getters and setters methods
@NoArgsConstructor // Generates a no-argument constructor for easy instantiation
@AllArgsConstructor // Generates a constructor with all fields as parameters
public class MarketingAutomation {
    private Long campaignId;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}