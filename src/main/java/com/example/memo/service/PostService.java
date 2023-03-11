package com.example.memo.service;

import com.example.memo.dto.request.ReqPostDto;
import com.example.memo.dto.response.RespPostDto;
import com.example.memo.dto.UserDetail;
import com.example.memo.entity.Post;
import com.example.memo.entity.User;
import com.example.memo.execption.NotAuthorizationException;
import com.example.memo.execption.NotFoundContentException;
import com.example.memo.repository.PostJpaRepository;
import com.example.memo.repository.UserJpaRepository;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostJpaRepository postRepo;
    private final UserJpaRepository userRepo;

    /**
     * 요청 받은 Post 정보를 DB에 저장하는 역할을 한다
     *
     * @param dto {@link Post}를 만들기 위한 post에 요청 정보
     * @param req 로그인 한 User만 Post를 작성 할 수 있으므로, 토큰을 확인하기 위한 {@link HttpServletRequest}
     * @return 만들어진 Post를 {@link RespPostDto}로 변환하여 반환
     * @throws NotAuthorizationException
     */

    public RespPostDto savePost(ReqPostDto dto, UserDetail detail) {

        Post post = new Post(dto);
        User user = userRepo.findById(detail.getUserId()).get();

        post.setUser(user);
        postRepo.save(post);

        return new RespPostDto(post);
    }

    /**
     * 전체 Post를 {@link RespPostDto}로 변환 후 List에 담아 반환 한다
     *
     * @return Post의 내용을 담은 {@link RespPostDto}로 변환 후 List에 담아 반환 한다
     */

    @Transactional(readOnly = true)
    public List<RespPostDto> getPosts() {

        List<Post> posts = postRepo.findAllByOrderByModifiedAtDesc();

        return posts.stream().map(RespPostDto::new).toList();

    }

    public boolean isPresent(Long postId){

        return getPostDto(postId) != null;
    }

    @Transactional(readOnly = true)
    public RespPostDto getPostDto(Long postId){

        RespPostDto dto = null;

        try{

            dto = new RespPostDto(getPost(postId));

        }catch (NotFoundContentException ignored){

        }

        return dto;
    }

    /**
     * PostService에서 공통으로 postId를 통해 {@link Post}를 얻기 위한 메소드
     *
     * @param postId 특정 Post를 찾기 위한 Post Id 값
     * @return 찾은 Post를 {@link Post} 로 반환 한다
     * @throws NotFoundContentException postId에 해당 하는 Post가 존재 하지 않을 시
     */
    @Transactional(readOnly = true)
    protected Post getPost(Long postId) throws NotFoundContentException {

        return postRepo.findById(postId).orElseThrow(NotFoundContentException::new);
    }

    /**
     * 해당 메소드는 주어진 PostID와 수정할 Post 내용으로 {@link Post}을 수정하고, 수정된 내용을 반환 한다
     *
     * @param postId 특정 Post를 찾기 위한 Post Id 값
     * @param dto    수정 하고자 하는 Post 내용
     * @param req    수정 Post에 대한 권한이 있는지 검사하기 위한 {@link HttpServletRequest}
     * @return PostId에 해당 하는 Post에 대해 수정 후, 수정 된 내용을 담아서 반환 한다
     * @throws NotAuthorizationException 해당 Post에 대한 수정 권한이 없을 시
     */

    //consider : update와 delete에서 발생하는 코드의 중복을 어떻게 제거 할 것인가?

    public RespPostDto updatePost(Long postId, ReqPostDto dto, UserDetail detail)
        throws NotAuthorizationException {

        Post post = getPost(postId);

        if(detail.isAdmin()){
            log.info("[admin update post] id : "+post.getId());

            return new RespPostDto(post);

        }else{
            if (matchUserAndPost(detail, post)) {
                post.update(dto);

                return new RespPostDto(post);

            } else {

                throw new NotAuthorizationException();
            }

        }

    }

    /**
     * 해당 메소드는 주어진 PostID을 가지고 해당 {@link Post}을 삭제 한다.
     *
     * @param postId 삭제할 {@link Post}의 ID
     * @param req    해당 Comment에 대해 삭제 권한이 있는지 확인 하기 위해, {@link HttpServletRequest}에서 로그인 인증 JWT을 얻어
     *               온다.
     * @throws NotAuthorizationException 해당 {@link Post}에 대한 삭제 권한이 없을 시
     */


    public void deletePost(Long postId,UserDetail detail)
        throws NotAuthorizationException{

        Post post = getPost(postId);

        if(detail.isAdmin()){

            log.info("[admin delete post] id : "+post.getId()+"");
            postRepo.delete(post);

        }else{

            if (matchUserAndPost(detail, post)) {
                postRepo.delete(post);

            } else {

                throw new NotAuthorizationException();
            }
        }
    }

    private boolean matchUserAndPost(UserDetail detail, Post post) {

        String userId = detail.getUserId();
        String userIdFromPost = post.getUser().getUserId();

        return userId.equals(userIdFromPost);
    }

}
