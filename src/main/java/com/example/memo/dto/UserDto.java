package com.example.memo.dto;

import com.example.memo.entity.UserRole;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
public class UserDto {

    @Pattern(regexp = "^[a-z0-9]{4,10}$")
    private final String userId;
    
    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$")
    private final String password;
    private final String userRole;
    private final String adminKey;

    public UserDto(String userId, String password, String userRole, String adminKey) {
        this.userId = userId;
        this.password = password;

        if(UserRole.fromStrToUserRole(userRole) == null){

            throw new IllegalArgumentException("허용되지 않는 User Type");
        }

        this.userRole = userRole;
        this.adminKey = adminKey;
    }

    public boolean isAdmin(String key){

        UserRole role = UserRole.fromStrToUserRole(this.userRole);

        return role == UserRole.ADMIN && this.adminKey.equals(key);
    }

}
