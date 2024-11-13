package com.example.demo.services;

public interface EncodeService {
    String encode(String pass);
    Boolean validate(String pass, String passEncode);
}
