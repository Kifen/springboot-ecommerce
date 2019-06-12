package com.springboot.ecommerce.springbootecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public final ResponseEntity<Object> handleResourceNotFoundException(ProductNotFoundException ex, WebRequest request){
        ErrorResponse errorItem =
                new ErrorResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(errorItem, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public final ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException ex, WebRequest request){
        ErrorResponse errorItem =
                new ErrorResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(errorItem, HttpStatus.NOT_FOUND);
    }
}
