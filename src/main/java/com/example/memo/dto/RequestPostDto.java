package com.example.memo.dto;

import com.example.memo.entity.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RequestPostDto {

    private Integer id;
    private String head;
    private String content;
    private String user_id;

    private String user_pwd;

    public RequestPostDto() {
    }

    public RequestPostDto(Post post){

        Objects.requireNonNull(post);

        this.id = post.getId();
        this.head = post.getHead();
        this.content = post.getContent();
        this.user_id = post.getUserId();
        this.user_pwd = post.getUserPwd();

    }

    public RequestPostDto(Integer id, String head, String content, String user_id, String user_pwd) {
        this.id = id;
        this.head = head;
        this.content = content;
        this.user_id = user_id;
        this.user_pwd = user_pwd;
    }


}
