package com.springboot.ecommerce.springbootecommerce.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(String message){
        super(message);
    }
}
