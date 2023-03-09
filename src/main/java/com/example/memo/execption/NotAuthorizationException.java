package com.example.memo.execption;

public class NotAuthorizationException extends RuntimeException{

    public NotAuthorizationException() {
    }

    public NotAuthorizationException(String message) {
        super(message);
    }
}
