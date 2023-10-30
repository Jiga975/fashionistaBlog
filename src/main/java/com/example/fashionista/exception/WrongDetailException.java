package com.example.fashionista.exception;

public class WrongDetailException extends RuntimeException{
    public WrongDetailException(String message) {
        super(message);
    }
}
