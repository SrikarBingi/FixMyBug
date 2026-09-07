package com.devrise.fixmybug.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devrise.fixmybug.dto.AnswerResponse;
import com.devrise.fixmybug.dto.CreateAnswerRequest;
import com.devrise.fixmybug.dto.UpdateAnswerRequest;
import com.devrise.fixmybug.service.AnswerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/questions/{questionId}/answers")
    public ResponseEntity<AnswerResponse> createAnswer(
            @PathVariable UUID questionId,
            @RequestParam UUID userId,
            @Valid @RequestBody CreateAnswerRequest request) {

        AnswerResponse response =
                answerService.createAnswer(questionId, userId, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/questions/{questionId}/answers")
    public ResponseEntity<List<AnswerResponse>> getAnswersByQuestion(
            @PathVariable UUID questionId) {

        return ResponseEntity.ok(
                answerService.getAnswersByQuestionId(questionId)
        );
    }

    @GetMapping("/answers/{answerId}")
    public ResponseEntity<AnswerResponse> getAnswerById(
            @PathVariable UUID answerId) {

        return ResponseEntity.ok(
                answerService.getAnswerById(answerId)
        );
    }

    @PutMapping("/answers/{answerId}")
    public ResponseEntity<AnswerResponse> updateAnswer(
            @PathVariable UUID answerId,
            @RequestParam UUID userId,
            @Valid @RequestBody UpdateAnswerRequest request) {

        return ResponseEntity.ok(
                answerService.updateAnswer(answerId, userId, request)
        );
    }

    @DeleteMapping("/answers/{answerId}")
    public ResponseEntity<Void> deleteAnswer(
            @PathVariable UUID answerId,
            @RequestParam UUID userId) {

        answerService.deleteAnswer(answerId, userId);

        return ResponseEntity.noContent().build();
    }
}