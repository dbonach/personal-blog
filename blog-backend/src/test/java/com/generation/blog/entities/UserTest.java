package com.generation.blog.entities;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserTest {

	public User user;
	public User emptyUser = new User();
	
	@Autowired
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	
	Validator validator = factory.getValidator();
	
	@BeforeEach
	public void start() {
		
//		LocalDate date = LocalDate.parse("2000-07-22", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		user = new User(0L, "Joao da Silva", "joao@email.com.br", "12345678");
	}
	
	@Test
	@DisplayName("Valida atributos nao nulos")
	void testValidaAtributos() {
		
		Set<ConstraintViolation<User>> violation = validator.validate(user);
		System.out.println(violation.toString());
		
		assertTrue(violation.isEmpty());
	}
	
	@Test
	@DisplayName("Nao valida atributos nulos")
	void testNaoValidaAtributos() {
		
		Set<ConstraintViolation<User>> violation = validator.validate(emptyUser);
		System.out.println(violation.toString());
		
		assertFalse(violation.isEmpty());
	}
}
