package com.farmer.farmermanagement.exception;

public class BankDetailsNotFoundException extends RuntimeException {

    
    public BankDetailsNotFoundException() {
        super("Bank details not found");
    }

    
    public BankDetailsNotFoundException(String message) {
        super(message);
    }

    
    public BankDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
