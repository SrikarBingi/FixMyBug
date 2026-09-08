package com.devrise.fixmybug.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devrise.fixmybug.dto.CommentResponse;
import com.devrise.fixmybug.dto.CreateCommentRequest;
import com.devrise.fixmybug.dto.UpdateCommentRequest;
import com.devrise.fixmybug.service.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/questions/{questionId}/comments")
    public ResponseEntity<CommentResponse> createComment(
            @PathVariable UUID questionId,
            @RequestParam UUID userId,
            @Valid @RequestBody CreateCommentRequest request) {

        CommentResponse response =
                commentService.createComment(questionId, userId, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/questions/{questionId}/comments")
    public ResponseEntity<List<CommentResponse>> getComments(
            @PathVariable UUID questionId) {

        return ResponseEntity.ok(
                commentService.getCommentsByQuestionId(questionId)
        );
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponse> getComment(
            @PathVariable UUID commentId) {

        return ResponseEntity.ok(
                commentService.getCommentById(commentId)
        );
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(
            @PathVariable UUID commentId,
            @RequestParam UUID userId,
            @Valid @RequestBody UpdateCommentRequest request) {

        return ResponseEntity.ok(
                commentService.updateComment(
                        commentId,
                        userId,
                        request
                )
        );
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable UUID commentId,
            @RequestParam UUID userId) {

        commentService.deleteComment(commentId, userId);

        return ResponseEntity.noContent().build();
    }
}