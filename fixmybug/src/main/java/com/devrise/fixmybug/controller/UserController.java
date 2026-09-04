package com.devrise.fixmybug.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devrise.fixmybug.dto.CreateUserRequest;
import com.devrise.fixmybug.dto.UpdateUserRequest;
import com.devrise.fixmybug.dto.UserResponse;
import com.devrise.fixmybug.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService=userService;
	}
	
	@PostMapping
	public ResponseEntity<UserResponse> saveUser(@Valid @RequestBody CreateUserRequest createUserRequest){
		
		UserResponse response = userService.createUser(createUserRequest);
		return ResponseEntity
				.status(201)
				.body(response);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserResponse> getUser(@PathVariable UUID userId){
		UserResponse response = userService.getUserById(userId);

	    return ResponseEntity.ok(response);
	}
	
	@GetMapping
	public ResponseEntity<List<UserResponse>> getAllUsers() {

	    List<UserResponse> users = userService.getAllUsers();

	    return ResponseEntity.ok(users);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserResponse> updateUser(
	        @PathVariable UUID userId,
	        @Valid @RequestBody UpdateUserRequest request) {

	    UserResponse response = userService.updateUser(userId, request);

	    return ResponseEntity.ok(response);
	}
}
