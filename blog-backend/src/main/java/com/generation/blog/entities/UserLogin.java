package com.generation.blog.entities;

public class UserLogin {

	private Long id;
	private String name;
	private String user;
	private String hashcode;
	private String token;
	private String photoUri;
	private String userType;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getHashcode() {
		return hashcode;
	}
	public void setHashcode(String hashcode) {
		this.hashcode = hashcode;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getPhotoUri() {
		return photoUri;
	}
	public void setPhotoUri(String photoUri) {
		this.photoUri = photoUri;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
}
