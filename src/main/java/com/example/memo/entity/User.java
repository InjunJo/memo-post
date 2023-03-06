package com.example.memo.entity;

import com.example.memo.dto.UserDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter
@Setter
public class User {

    @Id
    @Column(name = "USER_ID")
    private String userId;

    @Column(nullable = false, name = "USER_PWD")
    private String pwd;

    @OneToMany(mappedBy = "user")
    private List<PostTest> posts = new ArrayList<>();

    public User() {
    }

    public User(UserDTO dto){

        Objects.requireNonNull(dto);

        this.userId = dto.getUserId();
        this.pwd = dto.getPwd();
    }

}
