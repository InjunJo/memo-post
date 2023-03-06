package com.example.memo.service;

import com.example.memo.dto.UserDTO;
import com.example.memo.entity.User;
import com.example.memo.execption.DuplicateUserException;
import com.example.memo.execption.NotAuthException;
import com.example.memo.execption.NotFoundUserException;
import com.example.memo.repository.UserJpaRepository;
import com.example.memo.util.JwtUtil;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor
@Log4j2
public class UserService {

    private final UserJpaRepository userRepo;

    private final JwtUtil jwtUtil;

    @Transactional
    public void singUp(UserDTO dto){

        Objects.requireNonNull(dto);

        if(userRepo.findById(dto.getUserId()).isPresent()){

            throw new DuplicateUserException();
        }

        User user = new User(dto);

        userRepo.save(user);
    }

    public void login(UserDTO dto, HttpServletResponse resp){

        Objects.requireNonNull(dto);
        Objects.requireNonNull(resp);

        userRepo.findById(dto.getUserId()).orElseThrow(NotAuthException::new);

        String token = jwtUtil.createToken(dto.getUserId());

        resp.addHeader(JwtUtil.HEADER_KEY,token);
    }

    public User authorize(HttpServletRequest req){

        String token = jwtUtil.resolveToken(req);

        if(token == null || !jwtUtil.validateToken(token)){

            throw new NotAuthException();
        }

        String userId = jwtUtil.getClaims(token).getSubject();

        return userRepo.findById(userId).orElseThrow(NotFoundUserException::new);
    }

}
