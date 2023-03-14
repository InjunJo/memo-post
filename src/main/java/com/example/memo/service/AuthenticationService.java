package com.example.memo.service;

import com.example.memo.dto.UserDetail;
import com.example.memo.entity.UserRole;
import com.example.memo.execption.NotFoundUserException;
import com.example.memo.execption.NotValidatedTokenException;
import com.example.memo.repository.UserJpaRepository;
import com.example.memo.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Log4j2
@Service
public class AuthenticationService {

    private final JwtUtil jwtUtil;

    public String creatJwtToken(UserDetail detail) {

        return jwtUtil.createToken(detail);

    }

    public UserDetail authorizeByToken(String token)
        throws NotValidatedTokenException, NotFoundUserException {

        String userId = jwtUtil.getClaims(token).getSubject();
        String role = jwtUtil.getClaims(token).get(JwtUtil.AUTHORIZATION_KEY, String.class);

        return new UserDetail(userId, UserRole.fromStrToUserRole(role));
    }

}
