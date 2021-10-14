package com.generation.blog.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.generation.blog.entities.User;
import com.generation.blog.repositories.UserRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	private User user;
	private User userUpdate;
	private User userAdmin;

	@Autowired
	private UserRepository userRepository;

	@BeforeAll
	public void start() {

		userAdmin = new User(0L, "Administrador", "admin@email.com.br", "admin123");

		if (!userRepository.findByUser(userAdmin.getUser()).isPresent()) {

			HttpEntity<User> request = new HttpEntity<User>(userAdmin);
			testRestTemplate.exchange("/user/registration", HttpMethod.POST, request, User.class);
		}

		user = new User(0L, "Paulo Antunes", "paulo@email.com.br", "12345678");
		userUpdate = new User(1L, "Paulo Antunes de Souza", "paulo_souza@email.com.br", "12345678");
	}

	@Test
	@Order(1)
	@DisplayName("Cadastrar Usuario")
	public void deveRealizarPostUsuario() {

		HttpEntity<User> request = new HttpEntity<User>(user);

		ResponseEntity<User> response = testRestTemplate.exchange("/user/registration", HttpMethod.POST, request,
				User.class);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	@Order(2)
	@DisplayName("Listar todos os Usuarios!")
	public void deveMostrarTodosUsuarios() {

		ResponseEntity<String> response = testRestTemplate
				.withBasicAuth("admin@email.com.br", "admin123")
				.exchange("/user/all", HttpMethod.GET, null, String.class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	@Order(3)
	@DisplayName("Alterar Usuario")
	public void deveRealizarPutUsuario() {

		HttpEntity<User> request = new HttpEntity<User>(userUpdate);

		ResponseEntity<User> response = testRestTemplate.withBasicAuth("admin@email.com.br", "admin123")
				.exchange("/user/update", HttpMethod.PUT, request, User.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
