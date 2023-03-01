package com.example.memo.config;

import com.example.memo.response.NotFondPostResp;
import com.example.memo.response.UnAuthorizedResp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public UnAuthorizedResp unAuthorizedResp(){

        return new UnAuthorizedResp();
    }

    @Bean
    public NotFondPostResp notFondPostResp(){

        return new NotFondPostResp();
    }

}
