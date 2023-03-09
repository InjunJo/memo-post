package com.example.memo.advice;

import com.example.memo.execption.DuplicateUserException;
import com.example.memo.execption.NotValidatedTokenException;
import com.example.memo.execption.NotAuthorizationException;
import com.example.memo.execption.NotFoundCommentException;
import com.example.memo.execption.NotFoundPostException;
import com.example.memo.response.ResponseMsg;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class MemoRestControllerAdvice {

    @ExceptionHandler(NotFoundPostException.class) // post에 대한 update,delete
    public ResponseEntity<Object> handlePostNotFoundException(){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMsg("해당 Post 없음"));
    }
    @ExceptionHandler(NotAuthorizationException.class) //post 또는 comment에 대한 변경 작업 시
    public ResponseEntity<Object> handleNotAuthorization(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseMsg("해당 권한이 없습니다"));
    }

    @ExceptionHandler(NotValidatedTokenException.class) //로그인 실패
    public ResponseEntity<Object> handleNotAuthentication(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseMsg("로그인 실패"));
    }
    
    @ExceptionHandler(DuplicateUserException.class) //회원 가입 시
    public ResponseEntity<Object> handleDuplicatedUser(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMsg("이미 존재하는 사용자"));
    }

    @ExceptionHandler(NotFoundCommentException.class) // comment에 대한 update,delete
    public ResponseEntity<Object> handleNotFoundCmtException(){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMsg("해당 Comment 없음"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) //회원 가입 시
    public ResponseEntity<Object> handleSignUpValidException(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMsg("회원가입 유효성 오류"));
    }

}
