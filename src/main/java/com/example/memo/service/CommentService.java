package com.example.memo.service;

import com.example.memo.dto.request.ReqCommentDto;
import com.example.memo.dto.response.RespCommentDto;
import com.example.memo.dto.UserDetail;
import com.example.memo.entity.Comment;
import com.example.memo.entity.Post;
import com.example.memo.entity.User;
import com.example.memo.execption.NotAuthorizationException;
import com.example.memo.execption.NotFoundContentException;
import com.example.memo.execption.NotFoundUserException;
import com.example.memo.repository.CommentJpaRepository;
import com.example.memo.repository.PostJpaRepository;
import com.example.memo.repository.UserJpaRepository;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException.NotFound;

@Service
@RequiredArgsConstructor
@Log4j2
public class CommentService {

    private final CommentJpaRepository commentRepo;

    private final UserJpaRepository userRepo;

    private final PostJpaRepository postRepo;

    @Transactional
    public void createComment(Long postId, ReqCommentDto dto, UserDetail detail)
        throws NotFoundContentException, NotFoundUserException {

        Post post = postRepo.findById(postId).orElseThrow(NotFoundContentException::new);

        User user = userRepo.findById(detail.getUserId()).orElseThrow(NotFoundUserException::new);

        Comment comment = new Comment(dto, user, post);

        commentRepo.save(comment);
    }

    /**
     * CommentService에서 {@link Comment}를 필요로 하는 메소드를 위한 공통 메소드
     *
     * @param commentId 특정 {@link Comment}를 찾기 위한 식별자
     * @return commentId를 통해 찾은 {@link Comment}를 반환.
     * @throws NotFoundContentException 해당하는 {@link Comment}를 찾지 못할 시
     */

    private Comment findCommentById(Long commentId) throws NotFoundContentException {

        return commentRepo.findById(commentId)
            .orElseThrow(NotFoundContentException::new);
    }

    @Transactional(readOnly = true)
    public RespCommentDto getCommentDto(Long postId,Long commentId) throws NotFoundContentException {

        postRepo.findById(postId).orElseThrow(NotFoundContentException::new);

        return new RespCommentDto(findCommentById(commentId));
    }


    /**
     * 해당 메소드는 삭제를 요청 받으면, 삭제 요청에 대한 권한이 있는지 {@link HttpServletRequest}에서 로그인 인증 Jwt를 얻은 후, 삭제 권한이
     * 있는 User라면 해당 Comment를 삭제 한다
     *
     * @param commentId 삭제 하고자 하는 Comment의 ID 값
     * @param req       삭제 권한 확인을 위해 토큰이 담겨 있는 Request
     * @throws NotAuthorizationException 해당 Comment을 삭제 할 수 있는 권한이 없을 시
     */

    @Transactional
    public void deleteComment(Long postId, Long commentId, UserDetail detail)
        throws NotAuthorizationException, NotFoundContentException {

        postRepo.findById(postId).orElseThrow(NotFoundContentException::new);

        Comment comment = findCommentById(commentId);

        if (detail.isAdmin()) {
            commentRepo.delete(comment);
            log.info("[admin delete comment] id : " + comment.getId());

            //consider : log 작업의 중복을 제거 할 수 있는 방법이 뭐가 있을까? this.getClass()를 통한 방법?

        } else {

            if (matchUserAndComment(detail, comment)) {
                commentRepo.delete(comment);
            } else {
                throw new NotAuthorizationException("삭제 권한 없음");
            }

        }

    }


    /**
     * 해당 메소드는 주어진 Comment ID와 수정할 Comment 내용으로 Comment을 수정하고, 수정된 내용을 반환 한다
     *
     * @param commentId 수정할 Comment의 ID
     * @param reqCmt    수정할 Comment의 내용
     * @param req       해당 Comment에 대해 수정 권한이 있는지 확인 하기 위해, {@link HttpServletRequest}에서 로그인 인증 JWT을
     *                  얻어 온다
     * @return 변경된 Comment 내용을 반환 해줌.
     * @throws NotAuthorizationException Comment에 대한 수정 권한이 없을 시
     */

    @Transactional
    public RespCommentDto updateComment(Long postId, Long commentId, ReqCommentDto reqCmt,
        UserDetail detail) throws NotFoundContentException {

        postRepo.findById(postId).orElseThrow(NotFoundContentException::new);

        Comment comment = findCommentById(commentId);

        if (detail.isAdmin()) {
            comment.update(reqCmt);
            log.info("[admin update comment] id : " + comment.getId() + "");

        } else {

            if (matchUserAndComment(detail, comment)) {
                comment.update(reqCmt);
            } else {
                throw new NotAuthorizationException("수정 권한 없음");
            }
        }

        return new RespCommentDto(comment);

    }

    private boolean matchUserAndComment(UserDetail user, Comment cmt) {

        return user.getUserId().equals(cmt.getUser().getUserId());
    }


}
