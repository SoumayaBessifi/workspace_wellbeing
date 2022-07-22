package com.esprit.workspace_wellbeing.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esprit.workspace_wellbeing.entity.Quiz;
import com.esprit.workspace_wellbeing.entity.QuizInteraction;
import com.esprit.workspace_wellbeing.entity.User;



public interface QuizInteractionRepository extends JpaRepository<QuizInteraction, Integer> {
	 	Set<QuizInteraction> findByUser(User user);
	    
	   /* Set<QuizInteraction> findByTopic(Quiz topic);*/
	    
	    Set<QuizInteraction> findAllByOrderByCreationDateDesc();
	    
	    Set<QuizInteraction> findTop5ByOrderByCreationDateDesc();
	

}