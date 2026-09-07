package com.devrise.fixmybug.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateAnswerRequest {
	
	@NotBlank(message="Answer text cannot be empty")
	private String text;
	
	public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
	
}
