package com.esprit.workspace_wellbeing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.esprit.workspace_wellbeing.entity.Interaction;

@Repository
public interface InteractionRepository extends JpaRepository<Interaction, Long>{
	  @Query(value = "SELECT * FROM `interaction` WHERE post = ?1", nativeQuery = true)
	    List<Interaction> findAllCommentsOfPost(long postId);
	  
	  @Query(value = "SELECT * FROM `interaction` WHERE post = ?1 and user_interacter_id = ?2 ", nativeQuery = true)
	  Interaction findInteractionWithUserAndPostId(long postId,long userId);

	  @Query(value = "SELECT * FROM `interaction` WHERE user_interacter_id = ?1", nativeQuery = true)
	List<Interaction> findAllCommentsOfUser(long userId);

}
