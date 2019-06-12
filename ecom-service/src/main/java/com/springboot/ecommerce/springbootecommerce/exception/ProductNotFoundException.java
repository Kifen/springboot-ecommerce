package com.springboot.ecommerce.springbootecommerce.exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String message) {
        super(message);
    }
}
