package com.example.demo.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.services.EncodeService;

public class EncodeImpl implements EncodeService{

    @Override
    public String encode(String pass) {
        var encoder = new BCryptPasswordEncoder(10);
        return encoder.encode(pass);
    }

    @Override
    public Boolean validate(String password, String passEncode) {

        var encoder = new BCryptPasswordEncoder(10);

        if (!encoder.matches(password, passEncode))
            return false;
        
        return true;
    }
}
