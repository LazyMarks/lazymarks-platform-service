package com.lazymarks.platform.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lazymarks.platform.api.entity.User;
import com.lazymarks.platform.api.service.UserService;

import jakarta.validation.Valid;

@RestController
//@RequestMapping(path = "/user-service/v1/users")
public class UserController {

	@Autowired
	private UserService userService;

//	@PostMapping
	public ResponseEntity<Object> createUser(@RequestBody @Valid User user) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.createUser(user));
	}

//	@GetMapping("/{userId}")
	public ResponseEntity<Object> getUserById(@PathVariable Long userId) {
			return ResponseEntity.status(HttpStatus.OK).body(this.userService.getUserById(userId));
	}
}
