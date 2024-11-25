package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.JWTCreate;
import com.example.demo.dto.Token;
import com.example.demo.filters.JWTAuthenticationFilter;
import com.example.demo.impl.AnswerImpl;
import com.example.demo.impl.EncodeImpl;
import com.example.demo.impl.QuestionImpl;
import com.example.demo.impl.SpaceImpl;
import com.example.demo.impl.UserImpl;
import com.example.demo.services.AnswerService;
import com.example.demo.services.EncodeService;
import com.example.demo.services.JWTService;
import com.example.demo.services.QuestionService;
import com.example.demo.services.SpaceService;
import com.example.demo.services.UserService;

@Configuration
public class DependencyConfiguration {
    
    @Bean
    public UserService userService() {
        return new UserImpl();
    }

    @Bean
    public SpaceService spaceService() {
        return new SpaceImpl();
    }

    @Bean
    public QuestionService questionService() {
        return new QuestionImpl();
    }

    @Bean
    public AnswerService answerService() {
        return new AnswerImpl();
    }

    @Bean
    public EncodeService encodeService() {
        return new EncodeImpl();
    }

    @Bean
    public JWTService<Token> jwtService() {
        return new JWTCreate();
    }

    @Bean
    public JWTAuthenticationFilter JWTAuthenticationFilter() {
        return new JWTAuthenticationFilter();
    }

}