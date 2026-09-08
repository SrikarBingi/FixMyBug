package com.devrise.fixmybug.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCommentRequest {

    @NotBlank(message = "Comment text cannot be blank")
    @Size(max = 2000, message = "Comment cannot exceed 2000 characters")
    private String text;
}