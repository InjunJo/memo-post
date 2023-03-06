package com.example.memo.advice;

import com.example.memo.execption.NotAuthException;
import com.example.memo.execption.NotFoundPostException;
import com.example.memo.response.ResponseMsg;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class PostRestControllerAdvice {

    @ExceptionHandler(NotFoundPostException.class)
    public ResponseEntity<Object> handlePostNotFoundException(){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMsg("해당 Post 없음"));
    }
    @ExceptionHandler(NotAuthException.class)
    public ResponseEntity<Object> handleNotAuth(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseMsg("해당 권한이 없습니다"));
    }

}
