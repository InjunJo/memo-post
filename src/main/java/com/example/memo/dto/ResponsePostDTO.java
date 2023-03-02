package com.example.memo.dto;

import com.example.memo.entity.Post;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Builder @Setter
public class ResponsePostDTO {

    private Integer id;
    private String head;
    private String content;
    private String user_id;

    @JsonFormat(pattern = "yyyy/MM/dd/HH:mm:ss")
    private LocalDateTime createdAt;

    public ResponsePostDTO() {
    }

    public ResponsePostDTO(Integer id, String head, String content, String user_id,
        LocalDateTime createdAt) {
        this.id = id;
        this.head = head;
        this.content = content;
        this.user_id = user_id;
        this.createdAt = createdAt;
    }

    public ResponsePostDTO(Post post){
        this.id = post.getId();
        this.head = post.getHead();
        this.content = post.getContent();
        this.user_id = post.getUserId();
        this.createdAt = post.getCreatedAt();
    }

    public static ResponsePostDTO toPost(Post post){

        Objects.requireNonNull(post);

        return new ResponsePostDTO(post);
    }
}
