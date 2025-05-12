package com.cts.crm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.crm.dto.CustomerTicketResponseDTO;
import com.cts.crm.model.Tickets;
import com.cts.crm.service.CustomerSupportService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/support/tickets")
public class CustomerSupportController {

	private final CustomerSupportService customerSupportService;

//	@Autowired
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
	
	@DeleteMapping("/customer/{customerId}")
	@Transactional
	public String deleteAllTicketsByCustomerId(@PathVariable Long customerId) {
		return customerSupportService.deleteAllTicketsByCustomerId(customerId);
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
