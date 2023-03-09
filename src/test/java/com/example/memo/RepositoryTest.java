package com.example.memo;

import com.example.memo.entity.User;
import com.example.memo.repository.CommentJpaRepository;
import com.example.memo.repository.UserJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RepositoryTest {

    @Autowired
    private CommentJpaRepository commentRepo;


    @Autowired
    private UserJpaRepository userRepo;

    @Test
    void test1(){

    }

    @Test
    void testRepo(){
        User user = userRepo.findById("dignzh").get();

    }

}
