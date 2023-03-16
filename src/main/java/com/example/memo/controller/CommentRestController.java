package com.example.memo.controller;

import com.example.memo.dto.request.ReqCommentDto;
import com.example.memo.dto.response.RespCommentDto;
import com.example.memo.security.CustomUserDetails;
import com.example.memo.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class CommentRestController {

    private final CommentService commentService;

    @PostMapping("/{post_Id}/comment")
    public ResponseEntity<Object> creatComment(@PathVariable Long post_Id,
        @RequestBody ReqCommentDto dto,
        @AuthenticationPrincipal UserDetails details) {


        return ResponseEntity.ok(commentService.createComment(post_Id, dto, details));
    }

    @DeleteMapping("/{post_id}/comments/{comment_Id}")
    public ResponseEntity<Object> deleteComment(@PathVariable Long comment_Id,
        @PathVariable Long post_id, @AuthenticationPrincipal CustomUserDetails details) {

        commentService.deleteComment(post_id, comment_Id, details);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
            .body("댓글 삭제 완료");
    }

    @PutMapping("/{post_id}/comments/{comment_Id}")
    public ResponseEntity<RespCommentDto> updateComment(@PathVariable Long comment_Id,
        @PathVariable Long post_id, @RequestBody ReqCommentDto reqCmt,
        @AuthenticationPrincipal CustomUserDetails details) {

        RespCommentDto cmt =
            commentService.updateComment(post_id, comment_Id, reqCmt, details);

        return ResponseEntity.status(HttpStatus.CREATED).body(cmt);
    }

    @GetMapping("/{post_id}/comments/{comment_Id}")
    public ResponseEntity<RespCommentDto> getComment(@PathVariable Long comment_Id,
        @PathVariable Long post_id) {

        return ResponseEntity
            .ok(commentService.getCommentDto(post_id, comment_Id));
    }

}
