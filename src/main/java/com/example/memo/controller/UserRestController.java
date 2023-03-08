package com.example.memo.controller;

import com.example.memo.dto.UserDTO;
import com.example.memo.execption.DuplicateUserException;
import com.example.memo.execption.NotAuthException;
import com.example.memo.response.ResponseMsg;
import com.example.memo.service.UserService;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @Log4j2
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid UserDTO dto, HttpServletResponse resp){

        log.info("Login......."+dto);

        userService.login(dto,resp);

        return ResponseEntity.ok(new ResponseMsg("로그인 성공"));
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@RequestBody @Valid UserDTO dto){
        log.info("signUp......."+dto);

        userService.singUp(dto);

        return ResponseEntity.ok(new ResponseMsg("회원가입 완료"));
    }

}
