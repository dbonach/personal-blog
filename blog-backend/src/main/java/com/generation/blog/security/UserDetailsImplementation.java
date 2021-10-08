package com.generation.blog.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.generation.blog.entities.User;

public class UserDetailsImplementation implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String hashcode;
	
	public UserDetailsImplementation(User user) {
		this.userName = user.getUser();
		this.hashcode = user.getHashcode();
	}
	
	public UserDetailsImplementation() {
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return hashcode;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	
}
