package com.example.memo;

import com.example.memo.repository.UserJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class RepositoryTest {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Test
    void test1(){
        System.out.println(userJpaRepository);
    }

}
