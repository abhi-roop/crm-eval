package com.cts.crm.model;

import java.util.List;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId; // Unique identifier

    @NotBlank(message = "Name cannot be empty")
    @Column(nullable = false)
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    @Column(nullable = false, unique = true)
    private String email;

    @Size(min = 10, message = "Phone number must be at least 10 digits")
    @NotBlank(message = "Phone number is required")
    @Column(nullable = false)
    private String phone;

    @NotBlank(message = "Address is required")
    @Column(nullable = false)
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
}
