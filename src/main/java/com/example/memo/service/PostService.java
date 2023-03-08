package com.example.memo.service;

import com.example.memo.dto.ReqPostDto;
import com.example.memo.dto.RespCommentDto;
import com.example.memo.dto.RespPostDto;
import com.example.memo.entity.Comment;
import com.example.memo.entity.Post;
import com.example.memo.entity.User;
import com.example.memo.execption.NotAuthException;
import com.example.memo.execption.NotFoundPostException;
import com.example.memo.repository.CommentJpaRepository;
import com.example.memo.repository.PostJpaRepository;
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

    private final CommentJpaRepository commentRepo;

    private final UserService userService;

    @Transactional
    public RespPostDto savePost(ReqPostDto dto, HttpServletRequest req) {

        User user = userService.authorize(req);

        Post post = new Post(dto);

        post.setUser(user);
        postRepo.save(post);

        return new RespPostDto(post);
    }

    public void getPosts(){

        List<Post> posts = postRepo.findAllByOrderByModifiedAtDesc();

        log.info(posts);
/*
        return posts.stream().map(p -> new  ).collect(Collectors.toList());*/

    }

    public RespPostDto getPostDto(Long postId){

        Post post = postRepo.findById(postId).orElseThrow(NotFoundPostException::new);

        List<RespCommentDto> commentDtos = commentRepo.listOfPost(postId)
            .stream().map(RespCommentDto::new).toList();

        RespPostDto respPostDto = new RespPostDto(post);
        respPostDto.setComments(commentDtos);

        return respPostDto;
    }

    @Transactional
    protected Post getPost(Long postId){

        return postRepo.findById(postId).orElseThrow(NotFoundPostException::new);
    }


    @Transactional
    public RespPostDto updatePost(Long postId, ReqPostDto dto, HttpServletRequest req){
        User user = userService.authorize(req);

        Post post = postRepo.findById(postId)
            .orElseThrow(NotFoundPostException::new);

        if(matchUserAndPost(user,post)){
            post.update(dto);

            return new RespPostDto(post);

        }else{

            throw new NotAuthException();
        }

    }

    @Transactional
    public void deletePost(Long id,HttpServletRequest req){

        User user = userService.authorize(req);

        Post post = postRepo.findById(id).orElseThrow(NotFoundPostException::new);

        if(matchUserAndPost(user,post)){
            postRepo.delete(post);

        }else{

            throw new NotAuthException();
        }

    }

    private boolean matchUserAndPost(User user, Post post){

        String userId = user.getUserId();
        String userIdFromPost = post.getUser().getUserId();

        return userId.equals(userIdFromPost);
    }

}
