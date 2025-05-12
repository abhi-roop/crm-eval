package com.cts.crm.service;

import java.util.List;

import com.cts.crm.dto.CustomerTicketResponseDTO;
import com.cts.crm.model.Tickets;

public interface CustomerSupportService {
	List<Tickets> getAllTickets();

	CustomerTicketResponseDTO getTicketById(Long id);

	Tickets createTicket(Tickets ticket);

	Tickets updateTicket(Tickets ticket);

	void deleteTicket(Long id);

	List<Tickets> getTicketsByCustomerId(Long customerId);

	List<Tickets> getTicketsByStatus(String status);

	Tickets assignTicketToAgent(Long ticketId, String agent);

	Tickets updateTicketStatus(Long ticketId, String status);

	String deleteAllTicketsByCustomerId(Long customerId);

}
