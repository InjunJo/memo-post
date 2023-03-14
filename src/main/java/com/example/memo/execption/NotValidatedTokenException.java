package com.example.memo.execption;

public class NotValidatedTokenException extends RuntimeException {

    public NotValidatedTokenException(String message) {
        super(message);
    }
}
