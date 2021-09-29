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

import com.generation.blog.entities.BlogPost;
import com.generation.blog.repositories.BlogPostRepository;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BlogPostController {
	
	@Autowired
	private BlogPostRepository blogPostRepository;
	
	@GetMapping
	public ResponseEntity<List<BlogPost>> getAllBlogPost() {
		return ResponseEntity.ok(blogPostRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BlogPost> getBlogPostById(@PathVariable Long id) {
		return blogPostRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/title/{title}")
	public ResponseEntity<List<BlogPost>> getBlogPostByTitle(@PathVariable String title) {
		return ResponseEntity.ok(blogPostRepository.findAllByTitleContainingIgnoreCase(title));
	}
	
	@PostMapping
	public ResponseEntity<BlogPost> saveBlogPost(@RequestBody BlogPost blogPost) {
		return ResponseEntity.status(HttpStatus.CREATED).body(blogPostRepository.save(blogPost));
	}
	
	@PutMapping
	public ResponseEntity<BlogPost> updateBlogPost(@RequestBody BlogPost blogPost) {
		return ResponseEntity.status(HttpStatus.OK).body(blogPostRepository.save(blogPost));
	}
	
	@DeleteMapping("/{id}")
	public void deleteBlogPost(@PathVariable Long id) {
		blogPostRepository.deleteById(id);
	}
}
