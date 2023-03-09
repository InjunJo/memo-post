package com.example.memo.dto;

import com.example.memo.entity.Post;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RespPostDto {

    private final Long post_id;
    private final String head;
    private final String content;
    private final  String user_id;

    @JsonFormat(pattern = "yyyy/MM/dd/HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy/MM/dd/HH:mm:ss")
    private LocalDateTime modifiedAt;

    private List<RespComment> comments = new ArrayList<>();

    public RespPostDto(Post post){
        Objects.requireNonNull(post);

        this.post_id = post.getId();
        this.head = post.getHead();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.user_id = post.getUser().getUserId();

        comments = post.getComments().stream()
            .map(RespComment::new).collect(Collectors.toList());
    }
}
