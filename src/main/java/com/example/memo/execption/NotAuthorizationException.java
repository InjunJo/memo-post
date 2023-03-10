package com.example.memo.execption;

//consider : 커스텀 예외를 쓰는 것이 과연 효용성이 있는가? 표준 예외를 쓰는게 더 낫나??
public class NotAuthorizationException extends RuntimeException{

    public NotAuthorizationException() {
    }

    public NotAuthorizationException(String message) {
        super(message);
    }
}
