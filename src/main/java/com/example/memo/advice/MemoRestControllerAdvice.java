package com.example.memo.advice;

import com.example.memo.execption.DuplicateUserException;
import com.example.memo.execption.NotFoundUserException;
import com.example.memo.execption.NotAuthorizationException;
import com.example.memo.execption.NotFoundContentException;
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

    @ExceptionHandler(NotFoundContentException.class) // 존재하지 않는 자료 데이터에 대한 변경 작업 요청 시
    public ResponseEntity<Object> handleNotContentFoundException(){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMsg("작업을 수행할 대상 자료 없음"));
    }
    @ExceptionHandler({NotAuthorizationException.class}) // 자료 데이터 변경 작업에 대한 권한이 없을 때
    public ResponseEntity<Object> handleNotAuthorization(){

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseMsg("해당 권한이 없습니다"));
    }

    @ExceptionHandler({NotFoundUserException.class}) //로그인 실패
    public ResponseEntity<Object> handleNotAuthentication(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseMsg("로그인 실패"));
    }
    
    @ExceptionHandler(DuplicateUserException.class) //회원 가입에서 중복 아이디가 있을 시
    public ResponseEntity<Object> handleDuplicatedUser(DuplicateUserException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) //회원 가입에서 요청한 데이터가 유효성 검증을 통과하지 못할 시 
    public ResponseEntity<Object> handleSignUpValidException(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMsg("회원 가입 유효성 오류"));
    }

}
