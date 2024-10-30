package com.cysco.category.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e, WebRequest request) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                e.getMessage(),
                request.getDescription(true)
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ResourceNotFoundexception.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundexception(Exception e, WebRequest request) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND,
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                e.getMessage(),
                request.getDescription(true)
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
