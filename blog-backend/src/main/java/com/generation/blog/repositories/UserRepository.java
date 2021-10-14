package com.generation.blog.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.blog.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	public Optional<User> findByUser(String user);

	public User findByName(String name);

	public List<User> findAllByNameContainingIgnoreCase(String partialName);
}
