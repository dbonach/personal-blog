package com.generation.blog.services;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.generation.blog.entities.User;
import com.generation.blog.entities.UserLogin;
import com.generation.blog.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public User userRegistration(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String hashcode = encoder.encode(user.getHashcode());
		user.setHashcode(hashcode);
		
		return repository.save(user);
	}
	
	public Optional<UserLogin> login(Optional<UserLogin> userInRequest) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<User> user = repository.findByUser(userInRequest.get().getUser());
		
		if (user.isPresent()) {
			if (encoder.matches(userInRequest.get().getHashcode(), user.get().getHashcode())) {
				
				String auth = userInRequest.get().getUser() + ":" + userInRequest.get().getHashcode();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				
				userInRequest.get().setToken(authHeader);
				userInRequest.get().setName(user.get().getName());
				
				return userInRequest;
			}
		}
		
		return null;
	}
}
