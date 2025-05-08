package com.cts.crm.exception;

public class TicketNotFoundException extends RuntimeException {
	public TicketNotFoundException(String message) {
		super(message);
	}
}