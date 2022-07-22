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

import com.esprit.workspace_wellbeing.entity.Quiz;
import com.esprit.workspace_wellbeing.service.QuizService;

@RestController
@RequestMapping("/QuizController")
public class QuizController {
	
	@Autowired
    private QuizService quizService;
	
	 @PostMapping("/addQuiz/{userId}")
	 
	    public ResponseEntity<?> addQuiz(@Valid @RequestBody Quiz quiz,@PathVariable long userId){
	       return quizService.createQuiz(quiz,userId);
	    }
	
	 @GetMapping("/quiz")
	    public List<Quiz> getAllQuiz() {
	        return quizService.findAll();
	    }
	 @GetMapping("/quiz/{quiz_id}")
	    public Quiz findOne(@PathVariable Integer	 quiz_id ) {
	        return quizService.findOne(quiz_id);
	    }
	 
	 @GetMapping("/quiz/findAllByOrder")
	    public Set<Quiz> findAllByOrder() {
	        return quizService.findAllByOrderByCreationDateDesc();
	    }
	 
	@PatchMapping("/quiz/{quiz_id}")
	    public ResponseEntity<?> updateQuiz(@PathVariable Integer quiz_id, @Valid @RequestBody Quiz quizRequest) {
		 		return quizService.update(quiz_id , quizRequest);
	    }
	
	@DeleteMapping("/quiz/{quiz_id}")
	public  void deleteQuiz(@PathVariable Integer quiz_id) {
		 quizService.delete(quiz_id);
	}
	
}
