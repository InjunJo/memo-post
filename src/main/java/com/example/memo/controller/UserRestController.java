package com.example.memo.controller;

import com.example.memo.dto.UserDto;
import com.example.memo.execption.DuplicateUserException;
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
    public ResponseEntity<Object> login(@RequestBody @Valid UserDto dto, HttpServletResponse resp)
        throws NotValidatedTokenException {

        log.info("Login......." + dto);

        userService.login(dto, resp);

        return ResponseEntity.ok(new ResponseMsg("로그인 성공"));
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@RequestBody UserDto dto) throws DuplicateUserException {
        log.info("signUp......." + dto);

        userService.singUp(dto);

        return ResponseEntity.ok(new ResponseMsg("회원가입 완료"));
    }

    @PostMapping("/signup/admin")
    public void CreateAdmin(@RequestBody UserDto dto) {

    }

}
