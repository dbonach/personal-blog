package com.generation.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.generation.blog.entities.BlogPostTheme;

@Repository
public interface BlogPostThemeRepository extends JpaRepository<BlogPostTheme, Long> {
	
	public List<BlogPostTheme> findAllByNameContainingIgnoreCase(String name);
}
