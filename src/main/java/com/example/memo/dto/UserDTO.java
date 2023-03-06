package com.example.memo.dto;

import com.example.memo.entity.User;
import java.util.Objects;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter @RequiredArgsConstructor
@ToString
public class UserDTO {
    @Pattern(regexp = "^[a-z0-9]{4,10}$")
    private final String userId;

    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$")
    private final String password;

}
