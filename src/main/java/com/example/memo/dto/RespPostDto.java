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
    private final  String user_id;

    @JsonFormat(pattern = "yyyy/MM/dd/HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy/MM/dd/HH:mm:ss")
    private LocalDateTime modifiedAt;

    private List<RespCommentDto> comments = new ArrayList<>();

    public RespPostDto(Post post){
        Objects.requireNonNull(post);

        this.id = post.getId();
        this.head = post.getHead();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.user_id = post.getUser().getUserId();
    }

    public void setComments(List<RespCommentDto> comments) {
        this.comments = comments;
    }
}
