package com.devrise.fixmybug.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.devrise.fixmybug.dto.CreateUserRequest;
import com.devrise.fixmybug.dto.UpdateUserRequest;
import com.devrise.fixmybug.dto.UserResponse;
import com.devrise.fixmybug.exception.DuplicateUserException;
import com.devrise.fixmybug.exception.UserNotFoundException;
import com.devrise.fixmybug.model.User;
import com.devrise.fixmybug.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public UserResponse createUser(CreateUserRequest request) {
    	
    	if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateUserException("Username already exists");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateUserException("Email already exists");
        }
    	
    	User user = new User();
    	
    	user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        User savedUser = userRepository.save(user);

        return convertToResponse(savedUser);
    }
    
    public UserResponse getUserById(UUID userId) {

        User user = userRepository.findById(userId)
        		.orElseThrow(() ->
        	    new UserNotFoundException("User not found: " + userId)
        	);

        return convertToResponse(user);
    }
    
    public List<UserResponse> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users.stream()
                .map(this::convertToResponse)
                .toList();
    }
    
    public UserResponse updateUser(UUID userId, UpdateUserRequest request) {

        User user = userRepository.findById(userId)
        		.orElseThrow(() ->
        	    new UserNotFoundException("User not found: " + userId)
        	);

        if (request.getUsername() != null) {
            user.setUsername(request.getUsername());
        }

        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }

        if (request.getBio() != null) {
            user.setBio(request.getBio());
        }

        User updatedUser = userRepository.save(user);

        return convertToResponse(updatedUser);
    }
    
    private UserResponse convertToResponse(User user) {

        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getBio()
        );
    }
}
