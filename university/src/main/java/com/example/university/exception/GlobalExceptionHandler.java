package com.example.university.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCountryException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidCountryException(InvalidCountryException e){
        return new ResponseEntity<>(
                new ExceptionResponse(400 , "Country Not Found"),
                HttpStatus.BAD_REQUEST
        );
    }
}
