package com.cts.crm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CampaignNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDetails handleCampaignNotFoundException(CampaignNotFoundException ex) {
        return new ErrorDetails("error", ex.getMessage());
    }

    @ExceptionHandler(InvalidDateFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails handleInvalidDateFormatException(InvalidDateFormatException ex) {
        return new ErrorDetails("error", ex.getMessage());
    }
}
