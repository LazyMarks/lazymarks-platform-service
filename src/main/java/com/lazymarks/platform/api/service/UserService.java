package com.lazymarks.platform.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lazymarks.platform.api.entity.User;
import com.lazymarks.platform.api.model.GenericResponse;
import com.lazymarks.platform.api.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public GenericResponse<User> createUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setActive(true);
		return GenericResponse.success(this.userRepository.saveAndFlush(user));
	}

	public User getUserByUsernameOrEmail(String username) throws UsernameNotFoundException {
		return this.userRepository.findByUsernameOrEmail(username, username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

	public GenericResponse<User> getUserById(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found"));
		return GenericResponse.success(user);
	}
}
