package com.example.memo.service;

import com.example.memo.dto.request.ReqLoginDto;
import com.example.memo.dto.UserDetail;
import com.example.memo.dto.request.ReqSignUpDto;
import com.example.memo.entity.User;
import com.example.memo.entity.UserRole;
import com.example.memo.execption.DuplicateUserException;
import com.example.memo.execption.NotAuthenticationException;
import com.example.memo.execption.NotValidatedTokenException;
import com.example.memo.execption.NotFoundUserException;
import com.example.memo.repository.UserJpaRepository;
import com.example.memo.util.JwtUtil;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {

    private final UserJpaRepository userRepo;
    private final JwtUtil jwtUtil;

    @Value("${admin.key}")
    private String ADMIN_KEY;

    /**
     * User 중복 검사 후 정상 적이면 해당 User 정보를 DB에 저장 한다
     *
     * @param dto 회원 가입을 위해 보내온 요청 정보를 담는다.
     * @throws DuplicateUserException 회원 가입 하고자 하는 User ID가 중복 시
     */

    @Transactional
    public void singUp(ReqSignUpDto dto) throws DuplicateUserException {

        Objects.requireNonNull(dto);

        if (userRepo.findById(dto.getUserId()).isPresent()) {

            throw new DuplicateUserException();
        }

        User user = new User(dto);

        if (dto.isAdmin(ADMIN_KEY)) {

            user.setUserRole(UserRole.ADMIN);

        } else {

            user.setUserRole(UserRole.USER);
        }

        userRepo.save(user);
    }

    /**
     * 로그인 성공 후 JWT 토콘을 HttpResponse Header에 넣어 준다
     *
     * @param dto  JWT 토큰을 발행하기 위해 필요한 User 정보. id와 pwd를 담고 있다.
     * @param resp JWT 토큰 생성 후 토큰을 담기 위한 HttpRequest
     * @throws NotValidatedTokenException 로그인을 위한 정보가 담긴 {@link ReqSignUpDto}의 userId와 일치 하는 실제 유저가
     *                                    없을 시
     */

    public UserDetail login(ReqLoginDto dto)
        throws NotFoundUserException, NotAuthenticationException {

        User user = userRepo.findById(dto.getUserId())
            .orElseThrow(NotFoundUserException::new);

        if (user.getPassword().equals(dto.getPassword())) {

            return new UserDetail(user);

        } else {

            throw new NotAuthenticationException("로그인 실패");
        }

    }


    /**
     * 토큰을 통해 해당 작업에 대한 권한이 있는지 확인 한다.
     *
     * @param req Request Header에서 토큰을 얻기 위한 Request
     * @return 권한이 있으면 {@link User}를 반환 해준다
     * @throws NotValidatedTokenException 토큰이 존재 하지 않거나 유효 하지 않을 시
     * @throws NotFoundUserException      유효한 토큰 이지만 저장된 회원 정보가 없을 시
     */

    /*public UserDetail authorizeByToken(HttpServletRequest req)
        throws NotValidatedTokenException, NotFoundUserException {

        String token = jwtUtil.resolveToken(req);

        if (token == null || !jwtUtil.validateToken(token)) {

            throw new NotValidatedTokenException("토큰이 없거나 유효하지 않은 토큰");
        }

        String userId = jwtUtil.getClaims(token).getSubject();

        User user = userRepo.findById(userId).orElseThrow(NotFoundUserException::new);

        return new UserDetail(user);
    }*/

}
