package com.example.memo.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor @Service
@Slf4j
public class JwtAuthProviderService implements AuthenticationProvider {

    private final JpaUserDetailsService userDetailsService;



    @Override
    public Authentication authenticate(Authentication authentication)
        throws AuthenticationException {

        UserDetails details = userDetailsService
            .loadUserByUsername(authentication.getName());

        return  new UsernamePasswordAuthenticationToken(
            details,
            null,
            details.getAuthorities()
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
