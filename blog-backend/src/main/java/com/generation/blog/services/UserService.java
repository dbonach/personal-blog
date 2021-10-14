package com.generation.blog.services;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.blog.entities.User;
import com.generation.blog.entities.UserLogin;
import com.generation.blog.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User userRegistration(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String hashcode = encoder.encode(user.getHashcode());
		user.setHashcode(hashcode);

		return userRepository.save(user);
	}

	public Optional<UserLogin> login(Optional<UserLogin> userInRequest) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<User> user = userRepository.findByUser(userInRequest.get().getUser());

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

	public Optional<User> updateUser(User user) {

		if (userRepository.findById(user.getId()).isPresent()) {

			Optional<User> searchUser = userRepository.findByUser(user.getUser());

			if (searchUser.isPresent()) {

				if (searchUser.get().getId() != user.getId())
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario ja existe!", null);
			}
			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			String encodedPassword = encoder.encode(user.getHashcode());
			user.setHashcode(encodedPassword);
			
			return Optional.of(userRepository.save(user));
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario nao encontrado!", null);
		}
	}
}
