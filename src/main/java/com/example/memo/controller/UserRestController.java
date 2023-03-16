package com.example.memo.controller;

import com.example.memo.dto.UserDetail;
import com.example.memo.dto.request.ReqLoginDto;
import com.example.memo.dto.request.ReqSignUpDto;
import com.example.memo.execption.NotValidatedTokenException;
import com.example.memo.response.ResponseMsg;
import com.example.memo.service.UserService;
import com.example.memo.util.JwtUtil;
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

    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid ReqLoginDto dto, HttpServletResponse resp)
        throws NotValidatedTokenException {

        UserDetail userDetail = userService.login(dto);

        resp.addHeader(JwtUtil.HEADER_KEY, jwtUtil.createToken(userDetail));

        return ResponseEntity.ok(new ResponseMsg("로그인 성공"));
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@RequestBody ReqSignUpDto dto) {

        log.info(dto);

        userService.singUp(dto);

        return ResponseEntity.ok(new ResponseMsg("회원가입 완료"));
    }


}
