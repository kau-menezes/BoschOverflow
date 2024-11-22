package com.example.demo.impl;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.JWTCreate;
import com.example.demo.dto.LoginDto.LoginDto;
import com.example.demo.dto.LoginDto.LoginResponseDto;
import com.example.demo.dto.LoginDto.userReturn;
import com.example.demo.dto.Token;
import com.example.demo.dto.msg;
import com.example.demo.dto.UserDto.CreateUserDto;
import com.example.demo.models.UserEntity;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;

public class UserImpl implements UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private EncodeImpl encoder;

    @Override
    public ResponseEntity<Object> createUser(CreateUserDto newUserData) {
        var email = newUserData.email();
        var EDV = newUserData.EDV();
        var username = newUserData.username();
        var password = newUserData.password();


        // Conferindo se algum dos campos está vazio
        if (email.isEmpty() || EDV.isEmpty() || username.isEmpty() || password.isEmpty())
            return new ResponseEntity<>("Algum dos campos está vazio!", HttpStatus.BAD_REQUEST);

        // Conferindo se o email ou o edv já existem no banco
        var haveEmail = repo.findByEmail(email);
        var haveEDV = repo.findByEDV(EDV);


        if (!haveEmail.isEmpty() || !haveEDV.isEmpty())
            return new ResponseEntity<>("Usuário já cadastrado!", HttpStatus.UNPROCESSABLE_ENTITY);

        // Criando o usuário no banco
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setEmail(email);
        user.setEDV(EDV);
        user.setPassword(encoder.encode(password));

        repo.save(user);

        return new ResponseEntity<>(new msg("Usuário cadastrado com sucesso!"), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> login(LoginDto loginData) {
        var email = loginData.email();
        var edv = loginData.edv();
        var pass = loginData.password();
        
        if((email == null && edv == null) || pass == null)
            return new ResponseEntity<>("Campos nulos", HttpStatus.BAD_REQUEST);
        
        Optional<UserEntity> userOptional = Optional.empty();

        if (email != null && edv != null) {
            userOptional = repo.findByEmailOrEDV(email, edv);
        } else if (email != null) {
            userOptional = repo.findByEmailOrEDV(email, null);
        } else if (edv != null) {
            userOptional = repo.findByEmailOrEDV(null, edv);
        }

        if(userOptional.isEmpty()) 
            return new ResponseEntity<>("Usuário não encontrado", HttpStatus.NOT_FOUND);

        UserEntity user = userOptional.get();
        System.out.println(user);
        JWTCreate jwtCreate = new JWTCreate();
        Token token = new Token();
        token.setId(user.getUserId());
        token.setEDV(user.getEDV());
        
        if(!encoder.validate(pass, user.getPassword())) {
            return new ResponseEntity<>("Senha incorreta", HttpStatus.UNAUTHORIZED);
        }
        
        else {
            var jwt = "Bearer " + jwtCreate.get(token);

            return ResponseEntity
            .status(HttpStatus.OK)
            .body(new LoginResponseDto("Usuário logado com sucesso!", new userReturn(user.getUserId(), user.getEDV(), jwt)));
        }
    }
    

    @Override
    public boolean checkPassword(String password) {
        int maiusc = 0, minusc = 0, num = 0;

        if (password.isEmpty())
            return false;
            
        if (password.length() < 12)
            return false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

            if (Character.isUpperCase(c))
                maiusc++;

            if (Character.isLowerCase(c))
                minusc++;

            if (Character.isDigit(c))
                num++;
        }

        if (maiusc == 0)
            return false;
        
        if (minusc == 0)
            return false;

        return num != 0;
    }

    @Override
    public boolean checkEmail(String email) {
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
	    Matcher m = p.matcher(email);

        return m.matches();
    }


    @Override
    public boolean checkEdv(String edv) {

        if (edv.isEmpty())
            return false;
        
        if (edv.length() != 8)
            return false;

        if(edv.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*"))
            return false;

        return !edv.matches("[A-Z]*");
    }

    @Override
    public List<UserEntity> getUsers(String query, int page, int size) {
        // Se a query estiver vazia ou nula, busca todos os usuários com paginação
        if (query == null || query.isEmpty()) {
            // Busca todos os usuários na página específica
            Page<UserEntity> usersPage = repo.findAll(PageRequest.of(page, size));
            return usersPage.getContent();  // Retorna apenas os usuários
        }
    
        // Caso contrário, faz a busca com o filtro da query (exemplo, por nome)
        Page<UserEntity> usersPage = repo.findByUserIdContaining(query, PageRequest.of(page, size));
        return usersPage.getContent();  // Retorna apenas os usuários
    }

    
}
