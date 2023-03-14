package com.example.memo.entity;

import com.example.memo.dto.request.ReqCommentDto;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.ToString;

@Entity @Getter
@Table(indexes = {@Index(name = "idx_comment_post_id", columnList = "POST_ID")})
@ToString(exclude = {"user","post"})
public class Comment extends TimeStamp {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private Post post;

    public Comment() {
    }

    public Comment(ReqCommentDto dto,User user, Post post){

        Objects.requireNonNull(user);
        Objects.requireNonNull(post);

        this.content = dto.getContent();
        this.user = user;
        this.post = post;
    }

    public void update(ReqCommentDto updateCmt){
        this.content = updateCmt.getContent();
    }

}
