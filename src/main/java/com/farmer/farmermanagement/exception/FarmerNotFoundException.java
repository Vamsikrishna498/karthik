package com.farmer.farmermanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) 
public class FarmerNotFoundException extends RuntimeException {

    
    public FarmerNotFoundException() {
        super("Farmer not found");
    }

    
    public FarmerNotFoundException(String message) {
        super(message);
    }

    
    public FarmerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

