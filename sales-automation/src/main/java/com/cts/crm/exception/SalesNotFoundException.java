package com.cts.crm.exception;

public class SalesNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;  
    public SalesNotFoundException(String message) {
        super(message);
    }
}
