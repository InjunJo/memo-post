package com.example.memo.dto.request;

import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ReqLoginDto {

    @Pattern(regexp = "^[a-z0-9]{4,10}$")
    private final String userId;

    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$")
    private final String password;

}
