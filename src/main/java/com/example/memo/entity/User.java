package com.example.memo.entity;

import com.example.memo.dto.request.ReqSignUpDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity @Getter
@Setter @ToString
public class User {

    @Id
    @Column(name = "USER_ID")
    private String userId;

    @Column(nullable = false, name = "USER_PWD")
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public User() {
    }

    public User(ReqSignUpDto dto){

        Objects.requireNonNull(dto);

        this.userId = dto.getUserId();
        this.password = dto.getPassword();
        this.userRole = UserRole.ToUserRole(dto.getUserRole());
    }

}
