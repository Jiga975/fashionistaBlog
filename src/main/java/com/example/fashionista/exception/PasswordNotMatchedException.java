package com.example.fashionista.exception;

public class PasswordNotMatchedException extends RuntimeException{
    public PasswordNotMatchedException(String message) {
        super(message);
    }
}
