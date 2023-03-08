package com.example.memo.entity;

import com.example.memo.dto.ReqPostDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter @Entity @Setter
public class Post extends TimeStamp{

    @Id @GeneratedValue
    @Column(name = "POST_ID")
    private Long id;

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
