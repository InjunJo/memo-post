package com.example.memo.controller;


import com.example.memo.dto.request.ReqPostDto;
import com.example.memo.dto.response.RespPostDto;
import com.example.memo.dto.UserDetail;
import com.example.memo.execption.NotValidatedTokenException;
import com.example.memo.response.ResponseMsg;
import com.example.memo.service.AuthenticationService;
import com.example.memo.service.PostService;
import com.example.memo.service.UserService;
import com.example.memo.util.JwtUtil;
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
    private final AuthenticationService authService;

    private final JwtUtil jwtUtil;

    @PostMapping("/api/post")
    public ResponseEntity<Object> registerPost(@RequestBody ReqPostDto reqPostDto,
        HttpServletRequest req) {

        UserDetail detail
            = authService.authorizeByToken(filterRequest(req));

        RespPostDto postDto = postService.savePost(reqPostDto, detail);

        return ResponseEntity.status(HttpStatus.CREATED).body(postDto);
    }

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
        @RequestBody ReqPostDto dto, HttpServletRequest req) {

        UserDetail detail
            = authService.authorizeByToken(filterRequest(req));

        RespPostDto respDto = postService.updatePost(id,dto,detail);

        return ResponseEntity.status(HttpStatus.CREATED).body(respDto);
    }


    @DeleteMapping("/api/post/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable Long id,
        HttpServletRequest req) {


        UserDetail detail
            = authService.authorizeByToken(filterRequest(req));

        postService.deletePost(id, detail);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
            .body(new ResponseMsg("게시글 삭제 완료"));
    }

    private String filterRequest (HttpServletRequest req) throws NotValidatedTokenException{

        String token = jwtUtil.resolveToken(req);

        if (token == null || !jwtUtil.validateToken(token)) {

            throw new NotValidatedTokenException("유요하지 않은 토큰");
        }

        return token;
    }

}
