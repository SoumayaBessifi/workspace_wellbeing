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
import com.esprit.workspace_wellbeing.entity.QuizInteraction;
import com.esprit.workspace_wellbeing.entity.User;
import com.esprit.workspace_wellbeing.repository.QuizInteractionRepository;
import com.esprit.workspace_wellbeing.repository.QuizRepository;
import com.esprit.workspace_wellbeing.repository.UserRepository;
import com.esprit.workspace_wellbeing.security.jwt.response.ResponseMessage;
@Service
public class QuizInteractionService {
	@Autowired
    private QuizInteractionRepository quizInteractionRepository;
	@Autowired
    private UserRepository userRepository;
	@Autowired
	private QuizRepository quizRepository;
	public ResponseEntity<?> createQuiz(@Valid QuizInteraction quizInteraction, long userId , Integer quiz_id) {
		if(!userRepository.findById(userId).isPresent()) {
			return new ResponseEntity<>(new ResponseMessage("No user with this id"),
					HttpStatus.BAD_REQUEST);
		}
		if(!quizRepository.findById(quiz_id).isPresent()) {
			return new ResponseEntity<>(new ResponseMessage("No Quiz with this id is found"),
					HttpStatus.BAD_REQUEST);
		}
		quizInteraction.setUser(userRepository.findById(userId).get());
		quizInteraction.setQuiz(quizRepository.findById(quiz_id).get());
	        quizInteractionRepository.save(quizInteraction);
	       return ResponseEntity.ok("Quiz interaction saved successfully");
		}

		public List<QuizInteraction> findAll() {
			return quizInteractionRepository.findAll();
		}
		public QuizInteraction findOne(int quiz_id) {
			return quizInteractionRepository.findById(quiz_id).get();
		}
		
		 public Set<QuizInteraction> findAllByOrderByCreationDateDesc() {
		        return quizInteractionRepository.findAllByOrderByCreationDateDesc();
		    }
		 public Set<QuizInteraction> findTop5ByOrderByCreationDateDesc() {
		        return quizInteractionRepository.findTop5ByOrderByCreationDateDesc();
		    }
		/* public Set<QuizInteraction> findByTopic(int idTopic) {
		        return findByTopic(quizService.findOne(idTopic));
		    }
		    
		    public Set<QuizInteraction> findByTopic(Quiz topic) {
		        return quizInteractionRepository.findByTopic(topic);
		    }*/
		 
	    public Set<QuizInteraction> findByUser(User user) {
	        return quizInteractionRepository.findByUser(user);
	    }
	    

		public ResponseEntity<?> update(int quizInteraction_id, @Valid QuizInteraction quizInteractionRequest) {
			Optional<QuizInteraction> quizIntOptional = quizInteractionRepository.findById(quizInteraction_id);
			if(quizIntOptional.isPresent()) {
				QuizInteraction quizInt = quizIntOptional.get();
				quizInt.setAnswers(quizInteractionRequest.getAnswers());
			
		         quizInteractionRepository.save(quizInt);
		         return ResponseEntity.ok("Quiz interaction updated successfully");
			}
			else return new ResponseEntity<>(new ResponseMessage("No Quiz interaction with this id"),
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
		    
		    public void delete(QuizInteraction quiz) {
		    	quizInteractionRepository.delete(quiz);
		    }
		
		
}
