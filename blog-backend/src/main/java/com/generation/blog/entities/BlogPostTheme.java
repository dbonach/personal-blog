package com.generation.blog.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_themes")
public class BlogPostTheme {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 1, max = 200)
	private String description;
	
	@OneToMany(mappedBy = "theme", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("theme")
	private List<BlogPost> blogPosts;
	
//	public BlogPostTheme() {
//	}
//
//	public BlogPostTheme(Long id, @NotNull String description, List<BlogPost> blogPosts) {
//		this.id = id;
//		this.description = description;
//		this.blogPosts = blogPosts;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<BlogPost> getBlogPosts() {
		return blogPosts;
	}

	public void setBlogPosts(List<BlogPost> blogPosts) {
		this.blogPosts = blogPosts;
	}
}
