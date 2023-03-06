package com.example.memo.repository;

import com.example.memo.entity.Post;
import com.example.memo.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<Post,Integer> {

    List<Post> findAllByOrderByModifiedAtDesc();

    Optional<Post> findByIdAndUser(Integer id, User user);

}
