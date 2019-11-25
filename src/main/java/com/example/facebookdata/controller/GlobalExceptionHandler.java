package com.example.facebookdata.controller;

import com.example.facebookdata.exceptions.CustomErrorResponse;
import com.example.facebookdata.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleNotFound(Exception ex, WebRequest request) {
        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setError(ex.getMessage());
        errors.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleErr(Exception ex, WebRequest request) {
        CustomErrorResponse errors = new CustomErrorResponse();
//        errors.setError("Service is unavailable. Please try again later");
        errors.setError(ex.getMessage());
        errors.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());

        return new ResponseEntity<>(errors, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
