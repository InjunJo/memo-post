package com.example.memo.dto;

import com.example.memo.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public class UserDTO {

    private String userId;

    @JsonIgnore
    private String pwd;

    public UserDTO(User user){
        this.userId = user.getUserId();
        this.pwd = user.getPwd();
    }

}
