package com.example.memo.entity;

import com.example.memo.dto.RequestPostDto;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;


@Getter @Entity
public class Post extends TimeStamp{

    @Id @GeneratedValue
    @Column(name = "POST_ID")
    private Integer id;

    @Column(name = "HEAD", nullable = false)
    private String head;

    @Column(name = "CONTENTS")
    private String content;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "user_pwd", nullable = false)
    private String userPwd;


    public Post() {
    }

    public Post(RequestPostDto dto) {

        Objects.requireNonNull(dto);

        this.head = dto.getHead();
        this.content = dto.getContent();
        this.userId = dto.getUser_id();
        this.userPwd = dto.getUser_pwd();
    }

    public boolean checkPwd(RequestPostDto dto){

        Objects.requireNonNull(dto);

        String dtoPwd = dto.getUser_pwd();

        if(this.userPwd.equals(dtoPwd)){
            return true;
        }else{

            return false;
        }
    }

    public void update(RequestPostDto dto){

        Objects.requireNonNull(dto);

        this.head= dto.getHead();
        this.content = dto.getContent();
    }

}
