package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.services.UserService;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	UserService userService; // IMPLEMENTAR O USERSERVICE NA INJEÇÃO DE DEPENDÊNCIAS DA PASTA "config"

	@Test
	void checkPassword() { 
		assertEquals(userService.checkPassword("senha"), false); // curta, sem número, sem maiúscula 
		assertEquals(userService.checkPassword("senha123"), false); // curta, sem maiúscula
		assertEquals(userService.checkPassword("Senha123"), false); // curta
		assertEquals(userService.checkPassword("Minhasenha123"), true); // ótima 
		assertEquals(userService.checkPassword("MINHASENHA123"), false); // sem minúscula
	}

	@Test
	void checkEmail() {
		assertEquals(userService.checkEmail("email-já-existente-no-banco@mail.com"), false); // não pode cadastrar dois email iguais
		assertEquals(userService.checkEmail("email-novo@mail.com"), true);
	}

	@Test
	void checkUsername() {
		assertEquals(userService.checkUsername("username-em-uso"), false); // não pode cadastrar dois usernames iguais
		assertEquals(userService.checkEmail("username-novo"), true);
	}

	@Test
	void checkEdv() {
		assertEquals(userService.checkEdv("1234567"), false); // edv tem que ter exatos 8 números
		assertEquals(userService.checkEdv("123456789"), false); // edv tem que ter exatos 8 números
		assertEquals(userService.checkEdv("12345678"), true); // ótimo
		assertEquals(userService.checkEdv("11111111"), false); // ótimo MAS não pode cadastrar dois edvs iguais ent~so vê isso aí também finge que já tem o "11111111"
		assertEquals(userService.checkEdv("1234567a"), false); // não pode conter letras
		assertEquals(userService.checkEdv("1234567@"), false); // não pode conter caracteres especiais
	}

}
