package com.devrise.fixmybug.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devrise.fixmybug.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, UUID>{
	
	List<Answer> findByQuestionId(UUID questionId);
}
