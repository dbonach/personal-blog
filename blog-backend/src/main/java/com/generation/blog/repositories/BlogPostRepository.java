package com.generation.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.blog.entities.BlogPost;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long>{
	
	public List<BlogPost> findAllByTitleContainingIgnoreCase(String title);
}