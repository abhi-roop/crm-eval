package com.cts.crm.exception;

public class CampaignNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;  // Add this!

    public CampaignNotFoundException(String message) {
        super(message);
    }

    public CampaignNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
