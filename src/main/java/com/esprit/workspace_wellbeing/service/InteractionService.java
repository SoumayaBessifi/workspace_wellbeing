package com.esprit.workspace_wellbeing.service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.esprit.workspace_wellbeing.entity.Interaction;
import com.esprit.workspace_wellbeing.entity.Post;
import com.esprit.workspace_wellbeing.repository.InteractionRepository;
import com.esprit.workspace_wellbeing.repository.PostRepository;
import com.esprit.workspace_wellbeing.repository.UserRepository;


@Service
public class InteractionService {
	@Autowired
    private InteractionRepository interRepo;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private InteractionRepository interactionRepository;
	@Autowired
	private PostRepository postRepository;
	
	public Interaction commentPost(Interaction comment, long userId, long postId) {
		comment.setUserInteracterId(userRepository.findById(userId).get());
		comment.setPost(postRepository.findById(postId).get());
		comment.setUpdateHistory("");
		return interactionRepository.save(comment);
	}

	public List<Interaction> getAllCommentsOfPost(long postId) {
		return interactionRepository.findAllCommentsOfPost(postId);
	}

	public Interaction update(long interactionId, @Valid Interaction interactionRequest) {
		Optional<Interaction> interactiontOptional = interactionRepository.findById(interactionId);
		if(interactiontOptional.isPresent()) {
			Interaction interaction = interactiontOptional.get();
			interaction.setUpdateHistory(interaction.getUpdateHistory() + " changed in : " +new Date().toString() + " " + interaction.getCommentContent());
			interaction.setCommentContent(interactionRequest.getCommentContent());
	        return interactionRepository.save(interaction);
		}
		else throw new  ResourceNotFoundException("InteractionId : " + interactionId + " is no where to be found");
	}

	public ResponseEntity<?> detele(Long commentId) {
		Optional<Interaction> InteractionOptional = interactionRepository.findById(commentId);
		if(InteractionOptional.isPresent()) {
			Interaction interaction = InteractionOptional.get();
			interactionRepository.delete(interaction);
            return ResponseEntity.ok().build();

		}
		else throw new ResourceNotFoundException("comment with Id  " + commentId + " not found");
	}

	public List<Interaction> getAllCommentOfUser(long userId) {
		return interactionRepository.findAllCommentsOfUser(userId);
	}
	


	

}
