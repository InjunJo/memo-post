package com.example.memo.controller;

import com.example.memo.dto.UserDetail;
import com.example.memo.dto.request.ReqCommentDto;
import com.example.memo.dto.response.RespCommentDto;
import com.example.memo.execption.NotValidatedTokenException;
import com.example.memo.response.ResponseMsg;
import com.example.memo.service.AuthenticationService;
import com.example.memo.service.CommentService;
import com.example.memo.service.PostService;
import com.example.memo.util.JwtUtil;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final AuthenticationService authService;
    private final JwtUtil jwtUtil;

    @PostMapping("/{post_Id}/comment")
    public ResponseEntity<Object> creatComment(@PathVariable Long post_Id, @RequestBody ReqCommentDto dto,
        HttpServletRequest req) {

        UserDetail detail =
            authService.authorizeByToken(filterRequest(req));

        commentService.createComment(post_Id, dto, detail);

        return ResponseEntity.ok("삭제 완료");
    }

    @DeleteMapping("/{post_id}/comments/{comment_Id}")
    public ResponseEntity<Object> deleteComment(@PathVariable Long comment_Id,
        @PathVariable Long post_id, HttpServletRequest req) {

        UserDetail detail =
            authService.authorizeByToken(filterRequest(req));

        commentService.deleteComment(post_id,comment_Id, detail);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
            .body("댓글 삭제 완료");

    }

    @PutMapping("/{post_id}/comments/{comment_Id}")
    public ResponseEntity<RespCommentDto> updateComment(@PathVariable Long comment_Id,
        @PathVariable Long post_id, @RequestBody ReqCommentDto reqCmt, HttpServletRequest req) {

        UserDetail detail =
            authService.authorizeByToken(filterRequest(req));

        RespCommentDto cmt =
            commentService.updateComment(post_id,comment_Id, reqCmt, detail);

        return ResponseEntity.status(HttpStatus.CREATED).body(cmt);
    }

    @GetMapping("/{post_id}/comments/{comment_Id}")
    public ResponseEntity<RespCommentDto> getComment(@PathVariable Long comment_Id,
        @PathVariable Long post_id) {

        return ResponseEntity
            .ok(commentService.getCommentDto(post_id,comment_Id));
    }

    private String filterRequest (HttpServletRequest req) throws NotValidatedTokenException {

        String token = jwtUtil.resolveToken(req);

        if (token == null || !jwtUtil.validateToken(token)) {

            throw new NotValidatedTokenException("유요하지 않은 토큰");
        }

        return token;
    }


}
