package com.example.memo.service;

import com.example.memo.dto.ReqPostDto;
import com.example.memo.dto.RespPostDto;
import com.example.memo.entity.Post;
import com.example.memo.entity.User;
import com.example.memo.execption.NotAuthException;
import com.example.memo.execption.NotFoundPostException;
import com.example.memo.repository.PostJpaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2 @RequiredArgsConstructor
public class PostService {

    private final PostJpaRepository postRepo;

    private final UserService userService;

    @Transactional
    public RespPostDto savePost(ReqPostDto dto, HttpServletRequest req) {

        User user = userService.authorize(req);

        Post post = new Post(dto);

        post.setUser(user);
        postRepo.save(post);

        return new RespPostDto(post);
    }

    public List<RespPostDto> getPostList(){

        List<Post> posts = postRepo.findAllByOrderByModifiedAtDesc();

        return posts.stream().map(RespPostDto::new).collect(Collectors.toList());

    }

    public RespPostDto getPost(Integer postId){

        Post post = postRepo.findById(postId).orElseThrow(NotFoundPostException::new);

        return new RespPostDto(post);
    }

    @Transactional
    public RespPostDto updatePost(Integer postId, ReqPostDto dto, HttpServletRequest req){
        User user = userService.authorize(req);
        Post post = postRepo.findByIdAndUser(postId,user)
            .orElseThrow(NotAuthException::new);

        post.update(dto);

        return new RespPostDto(post);
    }

    @Transactional
    public void deletePost(Integer id,HttpServletRequest req){

        User user = userService.authorize(req);

        postRepo.findById(id).orElseThrow(NotFoundPostException::new);

        Post post = postRepo.findByIdAndUser(id,user)
            .orElseThrow(NotAuthException::new);

        postRepo.delete(post);
    }

}
