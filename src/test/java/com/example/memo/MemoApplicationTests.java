package com.example.memo;

import com.example.memo.repository.PostJpaRepository;
import com.example.memo.service.PostService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemoApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PostJpaRepository postJpaRepository;


    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PostService postService;


    @Test
    void contextLoads() {

    }

    @Test
    void testReposi(){

        System.out.println(entityManager);
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

}
