package com.esprit.workspace_wellbeing.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.esprit.workspace_wellbeing.entity.Quiz;
import com.esprit.workspace_wellbeing.entity.User;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer>   {

	Set <Quiz> findAllByOrderByCreationDateDesc();
	Set <Quiz> findByUser(User user);
}
