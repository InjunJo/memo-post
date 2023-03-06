package com.example.memo.repository;

import com.example.memo.entity.PostTest;
import com.example.memo.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTestRepository extends JpaRepository<PostTest,String> {

    List<PostTest> findAllByUser(User user);

    List<PostTest> findAllByUser_UserId(String id);

}
