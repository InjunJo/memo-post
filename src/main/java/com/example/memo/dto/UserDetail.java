package com.example.memo.dto;

import com.example.memo.entity.User;
import com.example.memo.entity.UserRole;
import lombok.Getter;

@Getter
public class UserDetail {

    private final String userId;

    private final UserRole role;

    public UserDetail(User user) {
        this.userId = user.getUserId();
        this.role = user.getUserRole();
    }

    public UserDetail(String userId, UserRole role) {
        this.userId = userId;
        this.role = role;
    }

    public boolean isAdmin(){

        return role == UserRole.ADMIN;
    }

}
