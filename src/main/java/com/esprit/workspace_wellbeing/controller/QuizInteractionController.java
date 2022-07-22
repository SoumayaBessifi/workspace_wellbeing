package com.esprit.workspace_wellbeing.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.workspace_wellbeing.entity.QuizInteraction;
import com.esprit.workspace_wellbeing.service.QuizInteractionService;


@RestController
@RequestMapping("/QuizIntController")
public class QuizInteractionController {
	@Autowired
    private QuizInteractionService quizInteractionService;
	 @PostMapping("/addQuizInt/{userId}/{quiz_id}")
	 
	    public ResponseEntity<?> addQuiz(@Valid @RequestBody QuizInteraction quizInt,@PathVariable long userId, @PathVariable Integer quiz_id ){
	       return quizInteractionService.createQuiz(quizInt,userId,quiz_id);
	    }
	
	 @GetMapping("/quizInt")
	    public List<QuizInteraction> getAllQuizInt() {
	        return quizInteractionService.findAll();
	    }
	 @GetMapping("/quizInt/{quizInt_id}")
	    public QuizInteraction findOne(@PathVariable Integer quizInt_id ) {
	        return quizInteractionService.findOne(quizInt_id);
	    }
	 
	 @GetMapping("/quizInt/findAllByOrder")
	    public Set<QuizInteraction> findAllByOrder() {
	        return quizInteractionService.findAllByOrderByCreationDateDesc();
	    }
	 @GetMapping("/quizInt/findTopFiveByDate")
	    public Set<QuizInteraction> findTop5ByOrderByCreationDateDesc() {
	        return quizInteractionService.findTop5ByOrderByCreationDateDesc();
	    }
	 
	@PatchMapping("/quizInt/{quizInt_id}")
	    public ResponseEntity<?> updateQuiz(@PathVariable Integer quizInt_id, @Valid @RequestBody QuizInteraction quizRequest) {
		 		return quizInteractionService.update(quizInt_id , quizRequest);
	    }
	
	
	@DeleteMapping("/quizInt/{quizInt_id}")
	public  void deleteQuiz(@PathVariable Integer quizInt_id) {
		quizInteractionService.delete(quizInt_id);
	}
}
