package com.example.memo.advice;

import com.example.memo.response.NotFondPostResp;
import com.example.memo.execption.PostNotFountException;
import com.example.memo.execption.PostPwdNotCorrectException;
import com.example.memo.response.UnAuthorizedResp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PostRestControllerAdvice {

    private final UnAuthorizedResp UN_AUTHOR_RESPONSE;

    private final NotFondPostResp NOT_FOUND_POST;

    public PostRestControllerAdvice(UnAuthorizedResp UN_AUTHOR_RESPONSE,
        NotFondPostResp NOT_FOUND_POST) {
        this.UN_AUTHOR_RESPONSE = UN_AUTHOR_RESPONSE;
        this.NOT_FOUND_POST = NOT_FOUND_POST;
    }

    @ExceptionHandler(PostNotFountException.class)
    public ResponseEntity<Object> handlePostNotFoundException(){

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(NOT_FOUND_POST);
    }

    @ExceptionHandler(PostPwdNotCorrectException.class)
    public ResponseEntity<Object> handlePostPwdException(){

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(UN_AUTHOR_RESPONSE);
    }

}
