package com.example.memo.dto;

import com.example.memo.entity.Post;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RespPostDto {

    private final Long id;
    private final String head;
    private final String content;


    @JsonFormat(pattern = "yyyy/MM/dd/HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy/MM/dd/HH:mm:ss")
    private LocalDateTime modifiedAt;

    public RespPostDto(Post post){
        Objects.requireNonNull(post);

        this.id = post.getId();
        this.head = post.getHead();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }
}
