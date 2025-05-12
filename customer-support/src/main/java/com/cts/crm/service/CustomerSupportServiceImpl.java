package com.cts.crm.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.crm.dto.CustomerSupportDTO;
import com.cts.crm.dto.CustomerTicketResponseDTO;
import com.cts.crm.exception.TicketNotFoundException;
import com.cts.crm.feignclient.CustomerClient;
import com.cts.crm.model.Tickets;
import com.cts.crm.repository.CustomerSupportRepository;

@Service
public class CustomerSupportServiceImpl implements CustomerSupportService {

	private final CustomerSupportRepository customerSupportRepository;

	//@Autowired
	public CustomerSupportServiceImpl(CustomerSupportRepository customerSupportRepository) {
		this.customerSupportRepository = customerSupportRepository;
	}

	@Autowired
	CustomerClient customerClient;
	@Override
	public List<Tickets> getAllTickets() {
		return customerSupportRepository.findAll();
	}

	@Override
	public CustomerTicketResponseDTO getTicketById(Long id) {
		Tickets ticket=  customerSupportRepository.findById(id).get();
		long customerId=ticket.getCustomerId();
		CustomerSupportDTO customerData=customerClient.getCustomerById(customerId);
		
		CustomerTicketResponseDTO responseDTO=new CustomerTicketResponseDTO(ticket, customerData);
		return responseDTO;
		
	}

	@Override
	public Tickets createTicket(Tickets ticket) {
	
		return customerSupportRepository.save(ticket);
	}

	@Override
	public Tickets updateTicket(Tickets ticket) {
	    return customerSupportRepository.findById(ticket.getTicketId())
	        .map(existingTicket -> {
	            existingTicket.setAssignedAgent(ticket.getAssignedAgent());
	            existingTicket.setStatus(ticket.getStatus());
	            existingTicket.setDescription(ticket.getDescription());

	            // Ensure 'subject' updates correctly
	            if (ticket.getSubject() != null) {
	                existingTicket.setSubject(ticket.getSubject());
	            }

	            existingTicket.setLastUpdatedDate(LocalDateTime.now());
	            return customerSupportRepository.save(existingTicket);
	        })
	        .orElseThrow(() -> new TicketNotFoundException("Ticket not found"));
	}



	@Override
	public void deleteTicket(Long id) {
		if (!customerSupportRepository.existsById(id)) {
			throw new TicketNotFoundException("Ticket with ID " + id + " not found");
		}
		customerSupportRepository.deleteById(id);
	}

	@Override
	public List<Tickets> getTicketsByCustomerId(Long customerId) {
		customerClient.getCustomerById(customerId);
		return customerSupportRepository.findByCustomerId(customerId);
	}

	@Override
	public List<Tickets> getTicketsByStatus(String status) {
		
		return customerSupportRepository.findByStatus(status);
	}

	@Override
	public Tickets assignTicketToAgent(Long ticketId, String agent) {
		return customerSupportRepository.findById(ticketId).map(ticket -> {
			ticket.setAssignedAgent(agent);
			ticket.setLastUpdatedDate(LocalDateTime.now());
			return customerSupportRepository.save(ticket);
		}).orElseThrow(() -> new TicketNotFoundException("Ticket with ID " + ticketId + " not found"));
	}

	@Override
	public Tickets updateTicketStatus(Long ticketId, String status) {
		return customerSupportRepository.findById(ticketId).map(ticket -> {
			ticket.setStatus(status);
			ticket.setLastUpdatedDate(LocalDateTime.now());
			return customerSupportRepository.save(ticket);
		}).orElseThrow(() -> new TicketNotFoundException("Ticket with ID " + ticketId + " not found"));
	}

	@Override
	public String deleteAllTicketsByCustomerId(Long customerId) {
		customerSupportRepository.deleteByCustomerId(customerId);
		return("All the tickets are deleted") ;
	}
}
