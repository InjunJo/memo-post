package com.example.memo;

import com.example.memo.entity.User;
import com.example.memo.repository.UserJpaRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

@DataJpaTest
public class RepositoryTest {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Test
    void test1(){
        System.out.println(userJpaRepository);

        String id = "dignzh1";

        Optional<User> opUser = userJpaRepository.findById(id);

        System.out.println(opUser.get());
    }

}
