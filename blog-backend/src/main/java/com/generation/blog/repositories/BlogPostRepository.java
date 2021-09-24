package com.generation.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.blog.entities.BlogPost;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long>{

}
