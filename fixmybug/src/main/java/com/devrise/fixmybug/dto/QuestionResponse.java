package com.devrise.fixmybug.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class QuestionResponse {

    private UUID id;
    private String title;
    private String description;
    private UUID userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public QuestionResponse(
            UUID id,
            String title,
            String description,
            UUID userId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public UUID getUserId() {
        return userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}