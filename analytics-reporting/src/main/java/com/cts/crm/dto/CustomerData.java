package com.cts.crm.dto;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerData {
    private Long customerId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private List<String> purchaseHistory;
    private Map<String, String> segmentationData;
    private String region;
    private String interests;
    private String purchasingHabits;
}