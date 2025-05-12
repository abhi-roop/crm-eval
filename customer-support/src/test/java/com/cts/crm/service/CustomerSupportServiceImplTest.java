package com.cts.crm.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cts.crm.dto.CustomerSupportDTO;
import com.cts.crm.dto.CustomerTicketResponseDTO;
import com.cts.crm.feignclient.CustomerClient;
import com.cts.crm.model.Tickets;
import com.cts.crm.repository.CustomerSupportRepository;

@ExtendWith(MockitoExtension.class)
class CustomerSupportServiceImplTest {

    @Mock
    private CustomerSupportRepository customerSupportRepository;

    @Mock
    private CustomerClient customerClient;

    @InjectMocks
    private CustomerSupportServiceImpl customerSupportService;

    private Tickets ticket;
    private CustomerSupportDTO customerData;

    @BeforeEach
    void setUp() {
        ticket = new Tickets();
        ticket.setTicketId(1L);
        ticket.setCustomerId(100L);
        ticket.setIssueDescription("Issue description");
        ticket.setStatus("OPEN");
        ticket.setAssignedAgent("Agent A");
        ticket.setSubject("Subject");
        ticket.setDescription("Detailed Description");
        ticket.setCreationDate(LocalDateTime.now());
        ticket.setLastUpdatedDate(LocalDateTime.now());

        customerData = new CustomerSupportDTO(100L, "John Doe", "johndoe@example.com", "1234567890", Arrays.asList("Product A", "Product B"));

        // Mock Feign client response
        when(customerClient.getCustomerById(100L)).thenReturn(customerData);
    }

    @Test
    void testGetTicketById() {
        // Mock repository response
        when(customerSupportRepository.findById(1L)).thenReturn(Optional.of(ticket));

        // Invoke service method
        CustomerTicketResponseDTO responseDTO = customerSupportService.getTicketById(1L);

        // Assertions
        assertNotNull(responseDTO);
        assertEquals("John Doe", responseDTO.getCustomerSupportDTO().getName());

        // Verifications
        verify(customerSupportRepository, times(1)).findById(1L);
        verify(customerClient, times(1)).getCustomerById(100L);
    }
}
