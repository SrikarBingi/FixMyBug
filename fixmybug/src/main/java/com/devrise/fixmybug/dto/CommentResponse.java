package com.devrise.fixmybug.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentResponse {

    private UUID id;
    private UUID parentId;
    private String text;
    private UUID userId;
    private LocalDateTime createdAt;
}