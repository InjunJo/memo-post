package com.example.memo.advice;

import com.example.memo.execption.DuplicateUserException;
import com.example.memo.execption.NotAuthException;
import com.example.memo.execption.NotFoundPostException;
import com.example.memo.response.ResponseMsg;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(NotFoundPostException.class)
    public ResponseEntity<Object> handleNotAuthException(){

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseMsg("로그인 실패"));
    }
    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<Object> handleDuplicatedUser(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMsg("이미 존재하는 사용자"));
    }

}
