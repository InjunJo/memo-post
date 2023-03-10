package com.example.memo.controller;

import com.example.memo.dto.ReqLoginDto;
import com.example.memo.dto.UserDto;
import com.example.memo.execption.NotValidatedTokenException;
import com.example.memo.response.ResponseMsg;
import com.example.memo.service.UserService;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid ReqLoginDto dto, HttpServletResponse resp)
        throws NotValidatedTokenException {
        
        //fixme : 해당 log에 대한 사용 목적이 확실하지 않음으로 일단 주석 처리
        /*log.info("Login......." + dto.getUserId());*/

        userService.login(dto, resp);

        return ResponseEntity.ok(new ResponseMsg("로그인 성공"));
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@RequestBody UserDto dto) {

        //fixme : 해당 log에 대한 사용 목적이 확실하지 않음으로 일단 주석 처리
        /*log.info("signUp......." + dto);*/

        userService.singUp(dto);

        return ResponseEntity.ok(new ResponseMsg("회원가입 완료"));
    }


}
