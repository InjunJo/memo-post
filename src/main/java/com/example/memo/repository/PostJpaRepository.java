package com.example.memo.repository;

import com.example.memo.entity.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<Post,Integer> {

    public List<Post> findAllByOrderByModifiedAtDesc();

}
