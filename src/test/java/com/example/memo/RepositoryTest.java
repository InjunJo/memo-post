package com.example.memo;

import com.example.memo.entity.User;
import com.example.memo.repository.CommentJpaRepository;
import com.example.memo.repository.UserJpaRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RepositoryTest {

    @Autowired
    private CommentJpaRepository commentRepo;

    @Test
    void test1(){
        System.out.println(commentRepo);
    }

}
