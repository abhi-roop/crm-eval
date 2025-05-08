package com.cts.crm.controller;

import com.cts.crm.dto.CustomerTicketResponseDTO;
import com.cts.crm.model.Tickets;
import com.cts.crm.service.CustomerSupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/support/tickets")
public class CustomerSupportController {

	private final CustomerSupportService customerSupportService;

	@Autowired
	public CustomerSupportController(CustomerSupportService customerSupportService) {
		this.customerSupportService = customerSupportService;
	}

	@GetMapping("/fetchAll")
	public List<Tickets> getAllTickets() {
		return customerSupportService.getAllTickets();
	}

	@GetMapping("/{id}")
	public CustomerTicketResponseDTO getTicketById(@PathVariable Long id) {
		return customerSupportService.getTicketById(id);
	}

	@PostMapping
	public Tickets createTicket(@RequestBody Tickets ticket) {
		return customerSupportService.createTicket(ticket);
	}

	@PutMapping("/{id}")
	public Tickets updateTicket (@RequestBody Tickets ticket) {
		return customerSupportService.updateTicket( ticket);
	}

	@DeleteMapping("/{id}")
	public void deleteTicket(@PathVariable Long id) {
		customerSupportService.deleteTicket(id);
	}

	@GetMapping("/customer/{customerId}")
	public List<Tickets> getTicketsByCustomerId(@PathVariable Long customerId) {
		return customerSupportService.getTicketsByCustomerId(customerId);
	}

	@PatchMapping("/{id}/assign")
	public Tickets assignTicketToAgent(@PathVariable Long id, @RequestParam String agent) {
		return customerSupportService.assignTicketToAgent(id, agent);
	}

	@PatchMapping("/{id}/status")
	public Tickets updateTicketStatus(@PathVariable Long id, @RequestParam String status) {
		return customerSupportService.updateTicketStatus(id, status);
	}
}
