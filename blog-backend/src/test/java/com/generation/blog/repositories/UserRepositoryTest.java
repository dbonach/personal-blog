package com.generation.blog.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.generation.blog.entities.User;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@BeforeAll
	void start() {
		
		User user = new User(0L, "Joao da Silva", "joao@email.com.br", "12345678");
		if (!userRepository.findByUser(user.getUser()).isPresent()) {
			userRepository.save(user);
		}
		
		user = new User(0L, "Manuel da Silva", "manuel@email.com.br", "12345678");
		if (!userRepository.findByUser(user.getUser()).isPresent()) {
			userRepository.save(user);
		}
		
		user = new User(0L, "Frederico da Silva", "frederico@email.com.br", "12345678");
		if (!userRepository.findByUser(user.getUser()).isPresent()) {
			userRepository.save(user);
		}
		
		user = new User(0L, "Paulo Antunes", "paulo@email.com.br", "12345678");
		if (!userRepository.findByUser(user.getUser()).isPresent()) {
			userRepository.save(user);
		}
	}
	
	@Test
	@DisplayName("Retorna o nome")
	public void findByNameRetornaNome() {
		User user = userRepository.findByName("Joao da Silva");
		assertTrue(user.getName().equals("Joao da Silva"));
	}
	
	@Test
	@DisplayName("Retorna 3 usuarios")
	public void findAllByNameContainingIgnoreCaseRetornaTresUsuarios() {
		
		List<User> listaDeUsuarios = userRepository
				.findAllByNameContainingIgnoreCase("Silva");
		assertEquals(3, listaDeUsuarios.size());
	}
	
	@AfterAll
	public void end() {
		System.out.println("Teste finalizado!");
	}
}
