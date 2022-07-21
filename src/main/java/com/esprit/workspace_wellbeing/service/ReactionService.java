package com.esprit.workspace_wellbeing.service;


import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.esprit.workspace_wellbeing.entity.Interaction;
import com.esprit.workspace_wellbeing.entity.Post;
import com.esprit.workspace_wellbeing.entity.Reactions;
import com.esprit.workspace_wellbeing.repository.InteractionRepository;
import com.esprit.workspace_wellbeing.repository.PostRepository;
import com.esprit.workspace_wellbeing.repository.ReactionRepository;
import com.esprit.workspace_wellbeing.repository.UserRepository;
import com.esprit.workspace_wellbeing.security.jwt.response.ResponseMessage;


@Service
public class ReactionService {
	@Autowired
    private InteractionRepository interRepo;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private InteractionRepository interactionRepository;
		
@Autowired
private PostRepository postRepository;
	@Autowired
	private ReactionRepository reactionRepository;

	public Post update(long interactionId, @Valid Interaction interactionRequest) {
			
		return null;
	}

	public String reactionMeaning(String numb) {
		switch(numb) {
		case "0":
			return "LIKE";
		case "1":
			return "HAHA";
		case "2":
			return "DISLIKE";
		case "3":
			return "LOVE";
		case "4":
			return "ANGRY";
		default : 
			return "ERROR";
		}
	}

	public ResponseEntity<?> reactToPost(@Valid Reactions react, long userId, long postId) throws Exception {
		try {
		react.setUserInteracterId(userRepository.findById(userId).get());
		react.setPostReaction(postRepository.findById(postId).get());
		}
		catch (Exception e) {
			return new ResponseEntity<>(new ResponseMessage("This post is nowhere to be found"),
					HttpStatus.BAD_REQUEST);
		}
		String reactMeaning ="";
		reactMeaning = this.reactionMeaning(react.getReaction().toString());
		if(reactMeaning.equals("ERROR"))
			return new ResponseEntity<>(new ResponseMessage("The reaction must be an integer"),
					HttpStatus.BAD_REQUEST);
		Reactions reaction = reactionRepository.findReaction(postId , userId);
		if(reaction == null) {
		 reactionRepository.save(react);
			return ResponseEntity.ok("Reacted with Succes");

		}
		if(reaction.getReaction().equals(react.getReaction()))
			return new ResponseEntity<>(new ResponseMessage("This user has already reacted to this post"),
					HttpStatus.BAD_REQUEST);
		else {
			reaction.setReaction(react.getReaction());
			reactionRepository.save(reaction);
			return ResponseEntity.ok("Updated react with Succes");			
		}
		
	}

	public List<Reactions> getAllReactuionsOfPost(long postId) {
		return reactionRepository.findAllReactionsOfPost(postId);		
	}

	public Reactions update(long reactionId, @Valid Reactions reactionRequest) {
		Optional<Reactions> ReactionOptional = reactionRepository.findById(reactionId);
		if(ReactionOptional.isPresent()) {
			Reactions reaction= ReactionOptional.get();
			reaction.setReaction(reactionRequest.getReaction());
	        return reactionRepository.save(reaction);
		}
		else throw new  ResourceNotFoundException("PostId " + reactionId + " not found");
	}



	public ResponseEntity<?> detele(Long reactionId) {
		Optional<Reactions> reactionOptional = reactionRepository.findById(reactionId);
		if(reactionOptional.isPresent()) {
			Reactions reaction= reactionOptional.get();
			reactionRepository.delete(reaction);
            return ResponseEntity.ok().build();

		}
		else throw new ResourceNotFoundException("Reaction with Id  " + reactionId + " not found");
	}


	public List<Reactions> getAllReactionsOfSpecificUser(long userId) {
		return reactionRepository.findAllReactionsOfUser(userId);		
	}
	
}
