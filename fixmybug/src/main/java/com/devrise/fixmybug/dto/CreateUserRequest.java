package com.devrise.fixmybug.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
	
	@NotBlank(message = "Username is required")
	private String username;
	
	@NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
	
	

	public CreateUserRequest() {
		super();
	}

	public CreateUserRequest(@NotBlank(message = "Username is required") String username,
			@NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email) {
		super();
		this.username = username;
		this.email = email;
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
	
	
}
