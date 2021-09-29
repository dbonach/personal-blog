package com.generation.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blog.entities.BlogPostTheme;
import com.generation.blog.repositories.BlogPostThemeRepository;

@RestController
@RequestMapping("/theme")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BlogPostThemeController {

	@Autowired
	private BlogPostThemeRepository blogPostThemeRepository;
	
	@GetMapping
	public ResponseEntity<List<BlogPostTheme>> getAllThemes() {
		return ResponseEntity.ok(blogPostThemeRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BlogPostTheme> getThemeById(@PathVariable Long id) {
		return blogPostThemeRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<List<BlogPostTheme>> getThemeByName(@PathVariable String name) {
		return ResponseEntity.ok(blogPostThemeRepository.findAllByDescriptionContainingIgnoreCase(name));
	}
	
	@PostMapping
	public ResponseEntity<BlogPostTheme> post(@RequestBody BlogPostTheme theme) {
		return ResponseEntity.status(HttpStatus.CREATED).body(blogPostThemeRepository.save(theme));
	}
	
	@PutMapping
	public ResponseEntity<BlogPostTheme> put(@RequestBody BlogPostTheme theme) {
		return ResponseEntity.ok(blogPostThemeRepository.save(theme));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		blogPostThemeRepository.deleteById(id);
	}
}