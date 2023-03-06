package com.example.memo.entity;

import com.example.memo.dto.ReqPostDto;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Getter @Entity @Setter
public class Post extends TimeStamp{

    @Id @GeneratedValue
    @Column(name = "POST_ID")
    private Integer id;

    @Column(name = "HEAD", nullable = false)
    private String head;

    @Column(name = "CONTENTS")
    private String content;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;


    public Post() {
    }

    public Post(ReqPostDto dto){

        Objects.requireNonNull(dto);

        this.head = dto.getHead();
        this.content = dto.getContent();
    }

    public void update(ReqPostDto dto){

        Objects.requireNonNull(dto);

        this.head= dto.getHead();
        this.content = dto.getContent();
    }

}
