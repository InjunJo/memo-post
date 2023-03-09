package com.example.memo.controller;


import com.example.memo.dto.ReqPostDto;
import com.example.memo.dto.RespPostDto;
import com.example.memo.execption.NotValidatedTokenException;
import com.example.memo.execption.NotFoundUserException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostRestController {

    private final PostService postService;

    @PostMapping("/post")
    public ResponseEntity<RespPostDto> upLoadPost(@RequestBody ReqPostDto reqPostDto,
        HttpServletRequest req) throws NotValidatedTokenException, NotFoundUserException {

        RespPostDto postDto = postService.savePost(reqPostDto,req);

        return ResponseEntity.status(HttpStatus.CREATED).body(postDto);
    }

    @GetMapping("/posts")
    public List<RespPostDto> findAllPosts() {
        log.warn("getPosts.......");

        return postService.getPosts();
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<RespPostDto> findPostById(@PathVariable Long id) {

        RespPostDto dto = postService.getPostDto(id);

        return ResponseEntity.ok(dto);

    }

    @PutMapping("/post/{id}")
    public ResponseEntity<RespPostDto> updatePost(@PathVariable Long id,
        @RequestBody ReqPostDto dto, HttpServletRequest req)
        throws NotValidatedTokenException, NotFoundUserException {

        RespPostDto respDto = postService.updatePost(id, dto,req);

        return ResponseEntity.status(HttpStatus.CREATED).body(respDto);
    }

    /**
     *
     * @param id
     * @param req
     * @return
     * @throws NotValidatedTokenException
     * @throws NotFoundUserException
     */

    @DeleteMapping("/post/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable Long id,
        HttpServletRequest req) throws NotValidatedTokenException, NotFoundUserException {

        postService.deletePost(id,req);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
            .body(new ResponseMsg("게시글 삭제 완료"));
    }

}
