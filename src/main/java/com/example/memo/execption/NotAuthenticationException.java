package com.example.memo.execption;

public class NotAuthenticationException extends RuntimeException{

    public NotAuthenticationException() {
    }

    public NotAuthenticationException(String message) {
        super(message);
    }
}
