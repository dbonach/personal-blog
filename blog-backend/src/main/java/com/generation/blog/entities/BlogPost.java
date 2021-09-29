package com.generation.blog.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_posts")
public class BlogPost {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 1, max = 100)
	private String title;
	
	@NotNull
	@Size(min = 10, max = 500)
	private String content;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date = new java.sql.Date(System.currentTimeMillis());
	
	@ManyToOne
	@JoinColumn(name = "theme_id")
	@JsonIgnoreProperties("blogPosts")
	private BlogPostTheme theme;
	
//	public BlogPost() {
//	}
//	
//	public BlogPost(String title, String content) {
//		this.title = title;
//		this.content = content;
//	}
//	
//	public BlogPost(Long id, String title, String content, BlogPostTheme theme) {
//		this.id = id;
//		this.title = title;
//		this.content = content;
//		this.theme = theme;
//	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public BlogPostTheme getTheme() {
		return theme;
	}
	public void setTheme(BlogPostTheme theme) {
		this.theme = theme;
	}
}
