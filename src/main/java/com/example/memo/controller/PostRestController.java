package com.example.memo.controller;

import com.example.memo.dto.PostDTO;
import com.example.memo.dto.RequestPostDto;
import com.example.memo.dto.ResponsePostDTO;
import com.example.memo.service.PostService;
import com.example.memo.service.PostTestService;
import java.util.List;
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
    public class PostRestController {

        private final PostService postService;

        private final PostTestService postTestService;


        public PostRestController(PostService postService, PostTestService postTestService) {
            this.postService = postService;
            this.postTestService = postTestService;
        }

        @GetMapping("/api/post/{id}")
        public ResponseEntity<ResponsePostDTO> getPostOne(@PathVariable Integer id) {

            ResponsePostDTO dto = postService.getPost(id);

            return ResponseEntity.ok(dto);

        }

        @GetMapping("/api/posts")
        public List<ResponsePostDTO> getPostList() {

            return postService.getPostList();
        }

        @PutMapping("/api/post/{id}")
        public ResponseEntity<Object> updatePost(@PathVariable Integer id,
            @RequestBody RequestPostDto requestPostDto) {

            ResponsePostDTO dto = postService.updatePost(id, requestPostDto);;

            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }

        @DeleteMapping("/api/post/{id}")
        public ResponseEntity<Object> deletePost(@PathVariable Integer id,
            @RequestBody RequestPostDto requestPostDto) {

            postService.deletePost(id, requestPostDto);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        }


        @PostMapping("/api/post")
        public ResponsePostDTO savePost(@RequestBody RequestPostDto requestPostDto) {

            return postService.savePost(requestPostDto);
        }

        @PostMapping("/api/test")
        public void testDTO(@RequestBody PostDTO dto){

            postTestService.savePost(dto);

        }

        @GetMapping("/api/test/{userId}")
        public List<PostDTO> getPostList(@PathVariable String userId){
            log.info(postTestService.getPostByUserId(userId));
            return postTestService.getPostByUserId(userId);
        }


    }
