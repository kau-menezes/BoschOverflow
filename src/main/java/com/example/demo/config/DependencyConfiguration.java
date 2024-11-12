package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.example.demo.services.UserService;

@Configuration
public class DependencyConfiguration {
    
    @Bean
    @Scope("singleton")
    public UserService userService() {
        return null; // retorna nulo só pra não dar erro rsrsrsrs IMPLEMENTAR O SERVIÇO DE USUÁRIO AQUI
    } 


}