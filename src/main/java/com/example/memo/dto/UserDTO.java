package com.example.memo.dto;

import com.example.memo.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class UserDTO {

    private String userId;

    private String pwd;

    public UserDTO() {
    }

    public UserDTO(User user){
        this.userId = user.getUserId();
        this.pwd = user.getPwd();
    }

    public UserDTO toDTO(User user){

        return new UserDTO(user);
    }

}
