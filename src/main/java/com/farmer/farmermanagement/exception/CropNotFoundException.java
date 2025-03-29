package com.farmer.farmermanagement.exception;

public class CropNotFoundException extends RuntimeException {

   
    public CropNotFoundException() {
        super("Crop not found");
    }

   
    public CropNotFoundException(String message) {
        super(message);
    }

    
    public CropNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
