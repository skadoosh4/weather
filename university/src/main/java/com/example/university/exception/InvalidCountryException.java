package com.example.university.exception;

public class InvalidCountryException extends RuntimeException {
    public InvalidCountryException(String message) {
        super(message);
    }
}
