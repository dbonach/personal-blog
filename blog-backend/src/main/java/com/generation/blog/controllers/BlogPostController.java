package com.generation.blog.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blog.entities.BlogPost;
import com.generation.blog.repositories.BlogPostRepository;

@RestController
@RequestMapping(value = "/posts")
public class BlogPostController {
	
	@Autowired
	BlogPostRepository blogPostRepository;

	@GetMapping
	public String hello() {
		return "Hello world";
	}
	
	@GetMapping(value = "/create")
	public BlogPost example() {
		
		BlogPost blogPost = new BlogPost("First Blog", "This is my first blog post", LocalDate.parse("2020-01-08"));
		
		blogPostRepository.save(blogPost);
		
		return blogPost;
	}
}
