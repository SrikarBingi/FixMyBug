package com.devrise.fixmybug.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.devrise.fixmybug.dto.AnswerResponse;
import com.devrise.fixmybug.dto.CreateAnswerRequest;
import com.devrise.fixmybug.dto.UpdateAnswerRequest;
import com.devrise.fixmybug.exception.QuestionNotFoundException;
import com.devrise.fixmybug.model.Answer;
import com.devrise.fixmybug.model.Question;
import com.devrise.fixmybug.model.User;
import com.devrise.fixmybug.repository.AnswerRepository;
import com.devrise.fixmybug.repository.QuestionRepository;
import com.devrise.fixmybug.repository.UserRepository;

@Service
public class AnswerService {
	
	private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public AnswerService(
            AnswerRepository answerRepository,
            QuestionRepository questionRepository,
            UserRepository userRepository) {

        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }
    
    public AnswerResponse createAnswer(
            UUID questionId,
            UUID userId,
            CreateAnswerRequest request) {
    	
    	Question question = questionRepository.findById(questionId)
    			.orElseThrow(()-> new QuestionNotFoundException("Question not found"));
    	
    	User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    	
    	Answer answer = new Answer();
    	
    	answer.setId(UUID.randomUUID());
        answer.setText(request.getText());
        answer.setQuestion(question);
        answer.setUser(user);
        answer.setCreatedAt(OffsetDateTime.now());

        Answer savedAnswer = answerRepository.save(answer);

        return mapToResponse(savedAnswer);
    }
    
    public List<AnswerResponse> getAnswersByQuestionId(UUID questionId){
    	
    	if(!questionRepository.existsById(questionId)) {
    		throw new QuestionNotFoundException("Question not found");
    	}
    	
    	return answerRepository.findByQuestionId(questionId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    
    public AnswerResponse getAnswerById(UUID answerId) {

        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new RuntimeException("Answer not found"));

        return mapToResponse(answer);
    }
    
    public AnswerResponse updateAnswer(
            UUID answerId,
            UUID userId,
            UpdateAnswerRequest request) {

        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new RuntimeException("Answer not found"));

        if (!answer.getUser().getId().equals(userId)) {
            throw new RuntimeException("You can only update your own answer");
        }

        answer.setText(request.getText());

        Answer updatedAnswer = answerRepository.save(answer);

        return mapToResponse(updatedAnswer);
    }
    
    public void deleteAnswer(UUID answerId, UUID userId) {

        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new RuntimeException("Answer not found"));

        if (!answer.getUser().getId().equals(userId)) {
            throw new RuntimeException("You can only delete your own answer");
        }

        answerRepository.delete(answer);
    }

    
    private AnswerResponse mapToResponse(Answer answer) {
    	
    	AnswerResponse response = new AnswerResponse();
    	
    	response.setId(answer.getId());
    	response.setText(answer.getText());
    	response.setQuestionId(answer.getQuestion().getId());
    	response.setUserId(answer.getUser().getId());
    	response.setCreatedAt(answer.getCreatedAt());
    	
    	return response;
    }
}
