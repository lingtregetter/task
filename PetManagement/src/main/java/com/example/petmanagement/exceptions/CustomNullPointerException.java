package com.example.petmanagement.exceptions;

public class CustomNullPointerException extends RuntimeException {
    /**
     * Custom null pointer exception, takes in custom message - for example 'User not found'
     * @param message - custom message to throw
     */
    public CustomNullPointerException(String message) {
        super(message);
    }
}
