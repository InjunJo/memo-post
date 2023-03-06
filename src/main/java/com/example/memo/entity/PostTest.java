package com.example.memo.entity;

import com.example.memo.dto.PostDTO;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity @Getter @Setter
@ToString
public class PostTest extends TimeStamp {

    @Id
    @GeneratedValue
    @Column(name = "POST_ID")
    private Integer id;

    @Column(nullable = false)
    private String head;

    @Column
    private String content;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "USER_ID")
    private User user;

    public PostTest() {
    }

    public PostTest(PostDTO dto){

        this.head = dto.getHead();
        this.content = dto.getContent();
        user = new User(dto.getUserDTO());

    }

    public PostTest(String head, String content, User user) {
        this.head = head;
        this.content = content;
        this.user = user;
    }
}
