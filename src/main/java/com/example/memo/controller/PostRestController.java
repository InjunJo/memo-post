package com.example.memo.controller;


import com.example.memo.dto.ReqPostDto;
import com.example.memo.dto.RespPostDto;
import com.example.memo.dto.UserDetail;
import com.example.memo.entity.User;
import com.example.memo.execption.NotValidatedTokenException;
import com.example.memo.execption.NotFoundUserException;
import com.example.memo.response.ResponseMsg;
import com.example.memo.service.PostService;
import com.example.memo.service.UserService;
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

    private final UserService userService;


    //todo : 중복되는 코드를 제거하기 위해 Filter라는 공통 메소드를 만드는 과정에서, User를 반환하는 메소드에서 예외 처리 시 ResponseEntity를 반환하는 문제
    /*private User userFilter(HttpServletRequest req){

        User user = null;

        try{

            user = userService.authorizeByToken(req);

        }catch (NotValidatedTokenException | NotFoundUserException e){

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseMsg("해당 권한이 없습니다"));
        }

        return user;
    }*/

    @PostMapping("/api/post")
    public ResponseEntity<Object> registerPost(@RequestBody ReqPostDto reqPostDto,
        HttpServletRequest req) {

        UserDetail userDetail = userService.authorizeByToken(req);

        RespPostDto postDto = postService.savePost(reqPostDto, userDetail);

        return ResponseEntity.status(HttpStatus.CREATED).body(postDto);
    }

    @GetMapping("/api/posts")
    public List<RespPostDto> findAllPosts() {
        log.warn("getPosts.......");

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

        UserDetail detail = userService.authorizeByToken(req);;

        RespPostDto respDto = postService.updatePost(id,dto,detail);

        return ResponseEntity.status(HttpStatus.CREATED).body(respDto);
    }


    @DeleteMapping("/api/post/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable Long id,
        HttpServletRequest req) {

        UserDetail detail = userService.authorizeByToken(req);;

        postService.deletePost(id, detail);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
            .body(new ResponseMsg("게시글 삭제 완료"));
    }

}
