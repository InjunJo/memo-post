package com.example.memo.service;

import com.example.memo.dto.RequestPostDto;
import com.example.memo.dto.ResponsePostDTO;
import com.example.memo.entity.Post;
import com.example.memo.execption.PostNotFountException;
import com.example.memo.execption.PostPwdNotCorrectException;
import com.example.memo.repository.PostJpaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
public class PostService {

    private final PostJpaRepository postRepo;

    public PostService(PostJpaRepository postRepo) {
        this.postRepo = postRepo;
    }

    @Transactional
    public ResponsePostDTO savePost(RequestPostDto dto){

        Post post = new Post(dto);
        postRepo.save(post);

        return ResponsePostDTO.toPost(post);

    }

    public ResponsePostDTO getPost(Integer postId){

        return ResponsePostDTO.toPost(findById(postId));
    }

    @Transactional
    public ResponsePostDTO updatePost(Integer postId, RequestPostDto dto){

        Post post = findById(postId);

        if(post.checkPwd(dto)){
            post.update(dto);
            return ResponsePostDTO.toPost(post);

        }else{
            throw new PostPwdNotCorrectException();
        }
    }

    @Transactional
    public ResponseEntity<Void> deletePost(Integer id, RequestPostDto dto){

        Post post = null;

        try {
            post = findById(id);
        }catch (PostNotFountException p){

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        if(post.checkPwd(dto)){
            postRepo.delete(post);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    public List<ResponsePostDTO> getPostList(){

        List<Post> postList = postRepo.findAllByOrderByModifiedAtDesc();

        List<ResponsePostDTO> RspDtoList = new ArrayList<>();

        for(Post p : postList){

            ResponsePostDTO dto = ResponsePostDTO.toPost(p);

            RspDtoList.add(dto);

        }

        return RspDtoList;
    }

    private Post findById(Integer postId){

        Optional<Post> opPost = postRepo.findById(postId);

        return opPost.orElseThrow(PostNotFountException::new);
    }

}
