package com.example.memo;

import com.example.memo.entity.User;
import com.example.memo.repository.PostJpaRepository;
import com.example.memo.repository.UserJpaRepository;
import com.example.memo.service.PostService;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemoApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PostJpaRepository postJpaRepository;

    @Autowired
    private UserJpaRepository userJpaRepository;


    @Autowired
    private PostService postService;


    @Test
    void contextLoads() {

    }

    @Test
    void testReposi(){

    }

    @Test
    void testDB(){

    }

    @Test
    void testReadUser(){


    }

    @Test
    void testPost(){
        /*User user = userRepository.findByUserId("dignzh");
        Post post = Post.builder().head("제목2")
            .content("내용2").user(user).build();

        postJpaRepository.save(post);*/

    }

    @Test
    void testGetJson(){

        System.out.println(postService.getPost(5));

    }

    @Test @Transactional
    void testUserRepo(){
        System.out.println(userJpaRepository);
        /*User user = new User();

        user.setUserId("dignzh");
        user.setPwd("1234");

        userJpaRepository.save(user);*/

        /*Optional<User> opUser = userJpaRepository.findById(user.getUserId());

        Assertions.assertEquals(user,opUser.get());*/

    }



}
