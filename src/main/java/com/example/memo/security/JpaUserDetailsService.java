package com.example.memo.security;

import com.example.memo.entity.User;
import com.example.memo.execption.NotFoundUserException;
import com.example.memo.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserJpaRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user= userRepo.findById(username).orElseThrow(NotFoundUserException::new);

        return new CustomUserDetails(user);
    }
}
