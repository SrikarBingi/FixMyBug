package com.devrise.fixmybug.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devrise.fixmybug.dto.CreateQuestionRequest;
import com.devrise.fixmybug.dto.QuestionResponse;
import com.devrise.fixmybug.exception.QuestionNotFoundException;
import com.devrise.fixmybug.exception.UserNotFoundException;
import com.devrise.fixmybug.model.Question;
import com.devrise.fixmybug.model.User;
import com.devrise.fixmybug.repository.QuestionRepository;
import com.devrise.fixmybug.repository.UserRepository;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public QuestionService(
            QuestionRepository questionRepository,
            UserRepository userRepository) {

        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    public QuestionResponse createQuestion(CreateQuestionRequest request) {
    	
    	System.out.println("CREATE QUESTION SERVICE CALLED");

        UUID userId = request.getUserId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Question question = new Question();

        question.setTitle(request.getTitle());
        question.setDescription(request.getDescription());
        question.setUser(user);

        Question savedQuestion = questionRepository.save(question);

        return new QuestionResponse(
                savedQuestion.getId(),
                savedQuestion.getTitle(),
                savedQuestion.getDescription(),
                savedQuestion.getUser().getId(),
                savedQuestion.getCreatedAt(),
                savedQuestion.getUpdatedAt()
        );
    }
    
    public QuestionResponse getQuestionById(UUID questionId) {

        Question question = questionRepository.findById(questionId)
        		.orElseThrow(() -> new QuestionNotFoundException("Question not found"));

        return new QuestionResponse(
                question.getId(),
                question.getTitle(),
                question.getDescription(),
                question.getUser().getId(),
                question.getCreatedAt(),
                question.getUpdatedAt()
        );
    }
    
    public Page<QuestionResponse> getAllQuestions(Pageable pageable){
    	
    	Page<Question> questions = questionRepository.findAll(pageable);
    	
    	return questions.map(question-> new QuestionResponse(
    			question.getId(),
                question.getTitle(),
                question.getDescription(),
                question.getUser().getId(),
                question.getCreatedAt(),
                question.getUpdatedAt()
                ));
    }
}