package com.devrise.fixmybug.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.devrise.fixmybug.dto.CommentResponse;
import com.devrise.fixmybug.dto.CreateCommentRequest;
import com.devrise.fixmybug.dto.UpdateCommentRequest;
import com.devrise.fixmybug.model.Comment;
import com.devrise.fixmybug.model.Question;
import com.devrise.fixmybug.model.User;
import com.devrise.fixmybug.repository.CommentRepository;
import com.devrise.fixmybug.repository.QuestionRepository;
import com.devrise.fixmybug.repository.UserRepository;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public CommentService(
            CommentRepository commentRepository,
            QuestionRepository questionRepository,
            UserRepository userRepository) {

        this.commentRepository = commentRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    public CommentResponse createComment(
            UUID questionId,
            UUID userId,
            CreateCommentRequest request) {

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() ->
                        new RuntimeException("Question not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Comment comment = new Comment();

        comment.setId(UUID.randomUUID());
        comment.setQuestion(question);
        comment.setUser(user);
        comment.setText(request.getText());
        comment.setCreatedAt(LocalDateTime.now());

        Comment savedComment = commentRepository.save(comment);

        return toResponse(savedComment);
    }

    public List<CommentResponse> getCommentsByQuestionId(UUID questionId) {

        return commentRepository
                .findByQuestionIdOrderByCreatedAtAsc(questionId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public CommentResponse getCommentById(UUID commentId) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() ->
                        new RuntimeException("Comment not found"));

        return toResponse(comment);
    }

    public CommentResponse updateComment(
            UUID commentId,
            UUID userId,
            UpdateCommentRequest request) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() ->
                        new RuntimeException("Comment not found"));

        if (!comment.getUser().getId().equals(userId)) {
            throw new RuntimeException(
                    "You are not allowed to update this comment");
        }

        comment.setText(request.getText());

        Comment updatedComment = commentRepository.save(comment);

        return toResponse(updatedComment);
    }

    public void deleteComment(UUID commentId, UUID userId) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() ->
                        new RuntimeException("Comment not found"));

        if (!comment.getUser().getId().equals(userId)) {
            throw new RuntimeException(
                    "You are not allowed to delete this comment");
        }

        commentRepository.delete(comment);
    }

    private CommentResponse toResponse(Comment comment) {

        return new CommentResponse(
                comment.getId(),
                comment.getQuestion().getId(),
                comment.getText(),
                comment.getUser().getId(),
                comment.getCreatedAt()
        );
    }
}