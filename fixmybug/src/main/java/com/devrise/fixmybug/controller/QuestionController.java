package com.devrise.fixmybug.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devrise.fixmybug.dto.CreateQuestionRequest;
import com.devrise.fixmybug.dto.QuestionResponse;
import com.devrise.fixmybug.service.QuestionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity<QuestionResponse> createQuestion(
            @Valid @RequestBody CreateQuestionRequest request) {

        QuestionResponse response =
                questionService.createQuestion(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
    
    @GetMapping("/{questionId}")
    public ResponseEntity<QuestionResponse> getQuestionById(
            @PathVariable UUID questionId) {

        QuestionResponse response =
                questionService.getQuestionById(questionId);

        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    public ResponseEntity<Page<QuestionResponse>> getAllQuestions(
            Pageable pageable) {

        Page<QuestionResponse> response =
                questionService.getAllQuestions(pageable);

        return ResponseEntity.ok(response);
    }
}