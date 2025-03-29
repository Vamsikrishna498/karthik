package com.farmer.farmermanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // Returns 404 when this exception is thrown
public class LandDetailsNotFoundException extends RuntimeException {

    // Default constructor with a generic message
    public LandDetailsNotFoundException() {
        super("Land details not found");
    }

    // Constructor with a custom message
    public LandDetailsNotFoundException(String message) {
        super(message);
    }

    // Constructor with message and cause (for exception chaining)
    public LandDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
