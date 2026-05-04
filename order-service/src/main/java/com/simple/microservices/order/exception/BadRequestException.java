package com.simple.microservices.order.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message){
        super(message);
    }
}