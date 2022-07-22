package com.esprit.workspace_wellbeing.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.esprit.workspace_wellbeing.entity.Quiz;
import com.esprit.workspace_wellbeing.entity.User;
import com.esprit.workspace_wellbeing.repository.QuizRepository;
import com.esprit.workspace_wellbeing.repository.UserRepository;
import com.esprit.workspace_wellbeing.security.jwt.response.ResponseMessage;
@Service
public class QuizService {

	@Autowired
    private QuizRepository quizRepository;
	@Autowired
    private UserRepository userRepository;
	
	public ResponseEntity<?> createQuiz(@Valid Quiz quiz, long userId) {
	if(!userRepository.findById(userId).isPresent()) {
		return new ResponseEntity<>(new ResponseMessage("No user with this id"),
				HttpStatus.BAD_REQUEST);
	}
	quiz.setUser(userRepository.findById(userId).get());
        quizRepository.save(quiz);
		return ResponseEntity.ok("Quiz saved successfully");
	}

	public List<Quiz> findAll() {
		return quizRepository.findAll();
	}
	public Quiz findOne(int quiz_id) {
		return quizRepository.findById(quiz_id).get();
	}
	
	 public Set<Quiz> findAllByOrderByCreationDateDesc() {
	        return quizRepository.findAllByOrderByCreationDateDesc();
	    }
	 
    public Set<Quiz> findByUser(User user) {
        return quizRepository.findByUser(user);
    }

	public ResponseEntity<?> update(int quiz_id, @Valid Quiz quizRequest) {
		Optional<Quiz> quizOptional = quizRepository.findById(quiz_id);
		if(quizOptional.isPresent()) {
			Quiz quiz = quizOptional.get();
			quiz.setContent(quizRequest.getContent());
			quiz.setTitle(quizRequest.getTitle());
			quiz.setTopic(quizRequest.getTopic());
			quiz.setContent(quizRequest.getContent());
	        return ResponseEntity.ok("Quiz updated successfully");
	   			}
	   			else return new ResponseEntity<>(new ResponseMessage("No quiz with this id"),
	   					HttpStatus.BAD_REQUEST);
		    
	}

	 
	    public ResponseEntity<?> delete(int id) {
	    	if(!quizRepository.findById(id).isPresent()) {
	    		return new ResponseEntity<>(new ResponseMessage("No quiz with this id found"),
	    				HttpStatus.BAD_REQUEST);
	    	}
	        delete(findOne(id));
	        return ResponseEntity.ok("Quiz deleted successfully");
	    }
	    
	    public void delete(Quiz quiz) {
	        quizRepository.delete(quiz);
	    }
	
	
}
