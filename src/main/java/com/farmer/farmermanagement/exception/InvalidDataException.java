package com.farmer.farmermanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) // Returns 400 when this exception is thrown
public class InvalidDataException extends RuntimeException {

    // Default constructor with a generic message
    public InvalidDataException() {
        super("Invalid input data provided");
    }

    // Constructor with a custom message
    public InvalidDataException(String message) {
        super(message);
    }

    // Constructor with message and cause (for exception chaining)
    public InvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
