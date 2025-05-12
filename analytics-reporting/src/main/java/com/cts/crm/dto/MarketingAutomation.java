package com.cts.crm.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketingAutomation {
    private Long campaignId;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}