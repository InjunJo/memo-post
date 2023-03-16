package com.example.memo.dto.request;

import com.example.memo.entity.UserRole;
import javax.transaction.NotSupportedException;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ReqSignUpDto {

    @Pattern(regexp = "^[a-z0-9]{4,10}$")
    private final String userId;
    
    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$")
    private final String password;

    private final String userRole;
    private final String adminKey;

    public ReqSignUpDto(String userId, String password, String userRole , String adminKey)
        throws NotSupportedException {

        this.userId = userId;
        this.password = password;

        if(UserRole.ToUserRole(userRole) == null){
            throw new NotSupportedException();
        }

        this.userRole = userRole;
        this.adminKey = adminKey;
    }

    public boolean isMatchedAdminKey(String key){

        return this.adminKey.equals(key);
    }

}
