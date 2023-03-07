package com.example.memo.dto;

import com.example.memo.entity.Comment;
import com.example.memo.entity.Post;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RespPostDto {

    private final Long id;
    private final String head;
    private final String content;

    private List<Comment> comments = new ArrayList<>();


    @JsonFormat(pattern = "yyyy/MM/dd/HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy/MM/dd/HH:mm:ss")
    private LocalDateTime modifiedAt;

    public RespPostDto(Post post,List<Comment> comments){
        Objects.requireNonNull(post);

        this.id = post.getId();
        this.head = post.getHead();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();

        if(comments != null){
            this.comments = comments;
        }
    }
}
