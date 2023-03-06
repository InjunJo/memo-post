package com.example.memo;

import com.example.memo.entity.Post;
import com.example.memo.entity.User;
import com.example.memo.repository.PostJpaRepository;
import com.example.memo.repository.UserJpaRepository;
import com.example.memo.service.PostService;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import org.junit.jupiter.api.Assertions;
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

    @PersistenceContext
    private EntityManager em;


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
        Post post = new Post();

        postJpaRepository.save(post);


    }

    @Test
    void testGetJson(){

    }

    @Test
    void testUserRepo(){

        User user = new User();

        user.setUserId("dignzh1");
        user.setPassword("1234");

        userJpaRepository.save(user);

        Optional<User> opUser = userJpaRepository.findById(user.getUserId());

        Assertions.assertEquals(user,opUser.get());


    }

    @Test
    void testLoadUser(){
        Optional<User> opUser = userJpaRepository.findById("dignzh1");

        System.out.println(opUser.get());
    }



    @Test @Transactional
    void postOfUser(){


        Optional<User> op = userJpaRepository.findById("dignzh1");

        User user = op.get();

        System.out.println(user.getPosts());

    }



}
