package com.cts.crm.exception;

public class TicketNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;  // Add this!

    public TicketNotFoundException(String message) {
        super(message);
    }
}
