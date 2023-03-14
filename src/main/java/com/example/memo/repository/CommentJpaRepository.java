package com.example.memo.repository;

import com.example.memo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<Comment,Long> {

}
