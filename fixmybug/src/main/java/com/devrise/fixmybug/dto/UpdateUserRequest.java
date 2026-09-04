package com.devrise.fixmybug.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

public class UpdateUserRequest {
	
	private String username;

    @Email(message = "Invalid email format")
    private String email;

    private String bio;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}
    
    
}
