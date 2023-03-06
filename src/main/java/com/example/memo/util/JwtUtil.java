package com.example.memo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Log4j2 @Component
public class JwtUtil {

    public static final String HEADER_KEY = "Authorization";

    public static final String VALUE_PREFIX = "Bearer ";


    private final Long EXPIRATION_TIME = 60 * 60 * 1000L;

    private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    private Key key;
    @Value("${jwt.secret.key}")
    private String SECRET_KEY;

    @PostConstruct
    public void init(){
        byte[] bytes = Base64.getDecoder().decode(SECRET_KEY);
        key = Keys.hmacShaKeyFor(bytes);
    }

    public String resolveToken(HttpServletRequest req){

        Objects.requireNonNull(req);

        String jwtHeader = req.getHeader(HEADER_KEY);

        if(StringUtils.hasText(jwtHeader) && jwtHeader.startsWith(VALUE_PREFIX)){

            return jwtHeader.substring(7);
        }

        return null;
    }

    public String createToken(String userId){

        Date date = new Date();

        return VALUE_PREFIX+
            Jwts.builder()
            .setSubject(userId)
            .setIssuedAt(date)
            .setExpiration(new Date(date.getTime()+EXPIRATION_TIME))
            .signWith(key,SIGNATURE_ALGORITHM).compact();
    }

    public boolean validateToken(String token){

        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token, 만료된 JWT token 입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
        }
        return false;

    }

    public Claims getClaims(String token){

        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

}
