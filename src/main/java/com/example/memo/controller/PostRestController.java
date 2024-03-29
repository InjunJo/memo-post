package com.example.memo.controller;


import com.example.memo.dto.request.ReqPostDto;
import com.example.memo.dto.response.RespPostDto;
import com.example.memo.response.ResponseMsg;
import com.example.memo.security.CustomUserDetails;
import com.example.memo.service.PostService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
public class PostRestController {

    private final PostService postService;

    @PostMapping("/api/post")
    public ResponseEntity<Object> registerPost(@RequestBody ReqPostDto reqPostDto,
        @AuthenticationPrincipal UserDetails details) {

        RespPostDto postDto = postService.savePost(reqPostDto, details.getUsername());

        return ResponseEntity.status(HttpStatus.CREATED).body(postDto);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/api/posts")
    public List<RespPostDto> findAllPosts() {

        return postService.getPosts();
    }

    @GetMapping("/api/post/{id}")
    public ResponseEntity<RespPostDto> findPostById(@PathVariable Long id) {

        RespPostDto dto = postService.getPostDto(id);

        return ResponseEntity.ok(dto);

    }

    @PutMapping("/api/post/{id}")
    public ResponseEntity<Object> updatePost(@PathVariable Long id,
        @RequestBody ReqPostDto dto, @AuthenticationPrincipal CustomUserDetails details) {


        RespPostDto respDto = postService.updatePost(id,dto,details);

        return ResponseEntity.status(HttpStatus.CREATED).body(respDto);
    }


    @DeleteMapping("/api/post/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable Long id,
        @AuthenticationPrincipal CustomUserDetails details) {

        postService.deletePost(id, details);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
            .body(new ResponseMsg("게시글 삭제 완료"));
    }

}
