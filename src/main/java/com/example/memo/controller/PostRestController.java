package com.example.memo.controller;


import com.example.memo.dto.ReqPostDto;
import com.example.memo.dto.RespPostDto;
import com.example.memo.response.ResponseMsg;
import com.example.memo.service.PostService;
import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
public class PostRestController {

    private final PostService postService;

    @PostMapping("/api/post")
    public ResponseEntity<RespPostDto> savePost(@RequestBody ReqPostDto reqPostDto,
        HttpServletRequest req) {

        RespPostDto postDto = postService.savePost(reqPostDto,req);

        return ResponseEntity.status(HttpStatus.CREATED).body(postDto);
    }

    @GetMapping("/api/posts")
    public List<RespPostDto> getPostList() {

        return postService.getPostList();
    }

    @GetMapping("/api/post/{id}")
    public ResponseEntity<RespPostDto> getPost(@PathVariable Long id) {

        RespPostDto dto = postService.getPost(id);

        return ResponseEntity.ok(dto);

    }

    @PutMapping("/api/post/{id}")
    public ResponseEntity<RespPostDto> updatePost(@PathVariable Long id,
        @RequestBody ReqPostDto dto, HttpServletRequest req) {

        RespPostDto respDto = postService.updatePost(id, dto,req);;

        return ResponseEntity.status(HttpStatus.OK).body(respDto);
    }

    @DeleteMapping("/api/post/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable Long id,
        HttpServletRequest req) {

        postService.deletePost(id,req);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
            .body(new ResponseMsg("게시글 삭제 성공"));
    }

}
