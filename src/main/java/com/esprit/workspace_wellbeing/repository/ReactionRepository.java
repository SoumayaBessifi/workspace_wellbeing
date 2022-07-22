package com.esprit.workspace_wellbeing.repository;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.esprit.workspace_wellbeing.entity.Post;
import com.esprit.workspace_wellbeing.entity.Reactions;

public interface ReactionRepository extends JpaRepository<Reactions, Long> {
	@Query(value = "SELECT * FROM `reactions` WHERE post_reaction = ?1", nativeQuery = true)
    List<Reactions> findAllReactionsOfPost(long postId);

	@Query(value = "SELECT * FROM `reactions` WHERE post_reaction = ?1 and user_interacter_id = ?2 ", nativeQuery = true)
	Reactions findReaction(long postId, long userId);

	@Query(value = "SELECT * FROM `reactions` WHERE user_interacter_id = ?1", nativeQuery = true)
	List<Reactions> findAllReactionsOfUser(long userId);

	
}
