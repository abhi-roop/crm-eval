package com.cts.crm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId; // Renamed 'id' to 'customerId' for clarity

    @Column(nullable = false)
    private String name;

    private String email;
    private String phone;
    private String address; 

    @ElementCollection
    @CollectionTable(name = "customer_purchase_history", joinColumns = @JoinColumn(name = "customer_id"))
    @Column(name = "product")
    private List<String> purchaseHistory; 

    @ElementCollection
    @CollectionTable(name = "customer_segmentation_data", joinColumns = @JoinColumn(name = "customer_id"))
    @MapKeyColumn(name = "segment_name")
    @Column(name = "segment_value")
    private Map<String, String> segmentationData; 

    private String region;
    private String interests;
    private String purchasingHabits;

    // Lombok will generate the no-args and all-args constructors
}