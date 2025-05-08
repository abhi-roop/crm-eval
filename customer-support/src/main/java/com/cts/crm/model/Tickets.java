package com.cts.crm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "support_tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tickets {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ticketId;

	@Column(nullable = false)
	private Long customerId;

	@Column(nullable = false)
	private String issueDescription;

	private String assignedAgent;

	@Column(nullable = false)
	private String status;
	@Column(nullable = false)
	private String subject;
	@Column(nullable = false)
	private String description;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime creationDate;

	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;

	
}