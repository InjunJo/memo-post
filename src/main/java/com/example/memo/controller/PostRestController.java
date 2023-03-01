package com.example.memo.controller;

import com.example.memo.dto.RequestPostDto;
import com.example.memo.dto.ResponsePostDTO;
import com.example.memo.service.PostService;
import java.util.List;
import lombok.extern.log4j.Log4j2;
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
public class PostRestController {

    private final PostService postService;

    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/api/post/{id}")
    public ResponsePostDTO getPostOne(@PathVariable Integer id){

        return postService.getPost(id);
    }

    @GetMapping("/api/posts")
    public List<ResponsePostDTO> getPostList(){

        return postService.getPostList();
    }

    @PutMapping("/api/post/{id}")
    public ResponsePostDTO updatePost(@PathVariable Integer id, @RequestBody RequestPostDto requestPostDto){

        log.info(requestPostDto);

        return postService.updatePost(id, requestPostDto);
    }

    @DeleteMapping("/api/post/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id, @RequestBody RequestPostDto requestPostDto){


        return postService.deletePost(id, requestPostDto);

    }


    @PostMapping("/api/post")
    public ResponsePostDTO savePost(@RequestBody RequestPostDto requestPostDto){

        return postService.savePost(requestPostDto);
    }


}
