package com.devrise.fixmybug.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devrise.fixmybug.model.Question;

public interface QuestionRepository extends JpaRepository<Question, UUID>{

}
