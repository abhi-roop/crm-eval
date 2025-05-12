package com.cts.crm.exception;

public class ErrorDetails {
    private String status;
    private String message;

    public ErrorDetails(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
