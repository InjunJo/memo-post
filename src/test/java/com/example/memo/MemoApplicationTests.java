package com.example.memo;

import com.example.memo.entity.PostTest;
import com.example.memo.entity.User;
import com.example.memo.repository.PostJpaRepository;
import com.example.memo.repository.PostTestRepository;
import com.example.memo.repository.UserJpaRepository;
import com.example.memo.service.PostService;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
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

    @PersistenceContext
    private EntityManager em;


    @Autowired
    private PostService postService;
    @Autowired
    private PostTestRepository postTestRepository;


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

    @Test
    void testUserRepo(){

        User user = new User();

        user.setUserId("dignzh1");
        user.setPwd("1234");

        userJpaRepository.save(user);

        /*Optional<User> opUser = userJpaRepository.findById(user.getUserId());

        Assertions.assertEquals(user,opUser.get());*/

    }

    @Test
    void testLoadUser(){
        Optional<User> opUser = userJpaRepository.findById("dignzh1");

        System.out.println(opUser.get());
    }

    @Test
    void testPostTest(){

        User user = new User();

        user.setUserId("dignzh1");
        user.setPwd("1234");

        PostTest post = new PostTest("제목1","내용1",user);

        user.getPosts().add(post);

        postTestRepository.save(post);

    }

    @Test
    void testPostAndUser(){

        User user = new User();

        user.setUserId("dignzh1");
        user.setPwd("1234");

        List<PostTest> list = postTestRepository.findAllByUser_UserId("dignzh1");

        System.out.println(list);


    }

    @Test @Transactional
    void postOfUser(){


        Optional<User> op = userJpaRepository.findById("dignzh1");

        User user = op.get();

        System.out.println(user.getPosts());

    }



}
