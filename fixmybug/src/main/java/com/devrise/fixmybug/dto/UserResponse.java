package com.devrise.fixmybug.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
	
	private UUID id;
    private String username;
    private String email;
    private String bio;
    
	public UserResponse() {
		super();
	}
	public UserResponse(UUID id, String username, String email, String bio) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.bio = bio;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
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
