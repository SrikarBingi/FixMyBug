package com.devrise.fixmybug.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devrise.fixmybug.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, UUID> {

    List<Comment> findByQuestionIdOrderByCreatedAtAsc(UUID questionId);
}