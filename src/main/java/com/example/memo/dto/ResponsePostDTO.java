package com.example.memo.dto;

import com.example.memo.entity.Post;
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

    public ResponsePostDTO() {
    }

    public ResponsePostDTO(Integer id, String head, String content, String user_id) {
        this.id = id;
        this.head = head;
        this.content = content;
        this.user_id = user_id;
    }

    public ResponsePostDTO(Post post){
        this.id = post.getId();
        this.head = post.getHead();
        this.content = post.getContent();
        this.user_id = post.getUserId();
    }

    public static ResponsePostDTO toPost(Post post){

        Objects.requireNonNull(post);

        return new ResponsePostDTO(post);
    }
}
