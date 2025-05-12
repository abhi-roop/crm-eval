package com.cts.crm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CampaignNotFoundException extends ResourceNotFoundException {

    private static final long serialVersionUID = 1L;

    public CampaignNotFoundException(String message) {
        super(message);
    }

    public CampaignNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}