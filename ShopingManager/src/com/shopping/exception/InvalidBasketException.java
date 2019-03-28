package com.shopping.exception;

public class InvalidBasketException extends RuntimeException {
    public InvalidBasketException(String message) {
        super(message);
    }
}
