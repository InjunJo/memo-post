package com.example.memo.dto;

import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter @RequiredArgsConstructor
@ToString
public class UserDto {

    @Pattern(regexp = "^[a-z0-9]{4,10}$")
    private final String userId;
    
    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$")
    private final String password;

    private final String userRole;

}
