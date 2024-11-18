package com.example.demo.services;
/* -------------------------------------------------------------------------- */
/*                              DTO ExtractedInfo                             */
/* -------------------------------------------------------------------------- */

import java.util.List;

import io.jsonwebtoken.Claims;

/*      criamos uma DTO chamada ExtracedInfo (Informação Extraída) :          */
/*  contém as informações necessárias do usuário:  username e id do usuário;  */

/*      no projeto do Trevisan, era chamado de Token, mas era confuso rs      */

public interface JWTService<T> {
        String get(T token);
        T validate(String jwt);
        List<String> getRoles(Claims claims);
        // public String generateToken(ExtractedInfo claims); // generateToken: gerar Token ->  recebe um json (dto) e transforma no token (string xxx.xxx.xxx)
        // public ExtractedInfo extractInfo(String token); // extractInfo: extrair informações -> recebe o token (string xxx.xxx.xxx) e retorna um json (dto) com as infomrmações extraídas
}
