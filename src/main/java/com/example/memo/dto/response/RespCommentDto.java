package com.example.memo.dto.response;

import com.example.memo.entity.Comment;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class RespCommentDto {

    private final Long comment_id;

    private final String content;

    private final LocalDateTime creatAt;

    private final String user_id;

    public RespCommentDto(Comment cmt){

        this.comment_id = cmt.getId();
        this.content = cmt.getContent();
        this.creatAt = cmt.getCreatedAt();
        this.user_id = cmt.getUser().getUserId();
    }


}
